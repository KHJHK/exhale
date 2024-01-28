<template>
  <div id="breadcrum">
      <RouterLink :to="{name: 'MainPage'}">메인 홈</RouterLink>
      >
      <RouterLink :to="{name: 'PostWholeListView'}">커뮤니티</RouterLink>
      >
      <RouterLink :to="{name: 'PostCreateView'}">글쓰기</RouterLink>
  </div>
  
  <div>
      <h2>게시글 수정</h2>
      <hr class="my-4" />
  </div>

  <form @submit.prevent="edit">
      <div class="mb-3">
      <!-- <label for="maintitle" class="form-label">제목</label> -->
      <input v-model="form.title" type="text" class="form-control" id="title" placeholder="제목을 입력하세요">
      </div>

      <div>
          (작성자명)
      </div>

      <hr class="my-4" />

      <div>
          (카테고리 선택)
      </div>

      <hr class="my-4" />

      <div class="mb-3">
      <label for="content" class="form-label">어떻게 쓰나요?</label>
      <textarea v-model="form.content" class="form-control" id="content" rows="3" placeholder="여기를 클릭해서 글을 작성하거나, 오른쪽 이미지나 이모티콘 아이콘을 클릭해서 첨부한 후 작성하면 됩니다."></textarea>
      </div>
      
      <div class="pt-4">
        <button type="button" class="btn btn-outline-danger me-2" @click="goDetailPage">취소</button>
        <button class="btn btn-primary">수정</button>
      </div>
  </form>

  <div class="pt-4">
      <button type="button" class="btn btn-outline-dark me-2">사진첨부</button>
      <button type="button" class="btn btn-outline-dark">이모티콘첨부</button>
  </div>

</template>

<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getPostById, updatePost } from '@/api/posts'

const route = useRoute()
const router = useRouter()
const id = route.params.id

const form = ref ({
  title: null,
  content: null,
})

const fetchPost = async () => {
    try {
        const { data } = await getPostById(id)
        setForm(data)
    } catch (error) {
        console.error(error)
    }
}
const setForm = ({ title, content }) => {
  form.value = { title, content }
}
fetchPost()

const edit = async () => {
  try {
    await updatePost(id, {...form.value})
    router.push({ name: 'PostDetailView', params: { id } })
  } catch (error) {
    console.error(error)
  }
}


const goDetailPage = () => router.push({ name: 'PostDetailView', params: { id } })

</script>

<style lang="scss" scoped>

</style>