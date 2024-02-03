<template>
    <div>
        <h2>{{ post.title }}</h2>
        <p>{{ post.content }}</p>
        <p class="text-muted">{{ post.create_date}}</p>
        <hr class="my-4" />
        <div class="row g-2">
            <div class="col-auto">
                <button class="btn btn-outline-dark" @click="navigateToPost(-1)">이전글</button>
            </div>
            <div class="col-auto">
                <button class="btn btn-outline-dark" @click="navigateToPost(1)">다음글</button>
            </div>
            <div class="col-auto me-auto"></div>
            <div class="col-auto">
                <button class="btn btn-outline-dark" @click="goListPage">목록</button>
            </div>
            <div class="col-auto">
                <button class="btn btn-outline-primary" @click="goEditPage">수정</button>
            </div>
            <div class="col-auto">
                <button class="btn btn-outline-danger" @click="remove" >삭제</button>
            </div>
        </div>
    </div>
    <hr>
    <div class="comments">
        <CommentsCreate/>
    </div>
    <div>

        <CommentsList :postId="Number(postId)" />
    </div>

  
  
  
</template>

<script setup>
import { getPostById, deletePost } from '@/api/posts';
import { ref, onMounted, watch, provide, reactive } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { defineProps } from 'vue';
import CommentsCreate from '@/components/comments/CommentsCreate.vue';
import CommentsList from '@/components/comments/CommentsList.vue';
import { getComments, getPosts } from '@/api/posts.js';



const router = useRouter()
const route = useRoute()
const postId = ref(route.params.id)
const posts = ref([])

const props = defineProps({
    postId: Number,
})

const post = ref({
    id : postId,
    title: null,
    content: null,
    create_date: null,
})

console.log
const fetchPosts = async () => {
  try {
    const response = await getPosts(); // 모든 게시글 불러오기
    posts.value = response.data;
  } catch (error) {
    console.error(error);
  }
};

onMounted(async () => {
  await fetchPosts();
});



const fetchPost = async (postId) => {
  try {
    const { data } = await getPostById(postId)
    // const response = await getPostById(postId);
    // post.value = response.data;
    post.value = data
    setPost(data)
  } catch (error) {
    console.error(error);
  }  
};

const setPost = ({title, content, create_date }) => {
    post.value.title = title
    post.value.content = content
    post.value.create_date = create_date
}
fetchPost()

const remove = async () => {
    try {
        if (confirm('삭제 하시겠습니까?') === false) {
            return
        }
        await deletePost(id)
        router.push({ name: 'PostWholeListView' })
    } catch (error) {
        console.error(error)
    }
}

const fetchComments = async (postId) => {
  try {
    const response = await getComments(postId);
    comments.value = response.data
   
  } catch (error) {
    console.error('댓글을 불러오던 중 강도를 만났어요',error);
  }
};


const navigateToPost = (direction) => {
    if (posts.value && posts.value.length > 0) {
  const currentIndex = posts.value.findIndex((p) => p.id === post.value.id);
  const nextIndex = currentIndex + direction;
  
  
  if (nextIndex >= 0 && nextIndex < posts.value.length) {
    router.push({ name: 'PostDetailView', params: { id: posts.value[nextIndex].id } });
  }
  if (currentIndex === -1) {
    console.error('현재 게시글을 찾을 수 없습니다.');
    return;
  }

  if (nextIndex < 0) {
    alert('첫 번째 게시물입니다.');
  } else if (nextIndex >= posts.value.length) {
    alert('마지막 게시물입니다.');
  } else {
    router.push({ name: 'PostDetailView', params: { id: posts.value[nextIndex].id } });
  }
};
};

const goListPage = () => router.push({name: 'PostWholeListView'})
const goEditPage = () => {
  const id = post.value.id || props.id
  router.push({ name: 'PostEditView', params: { id } })
}

watch(() => route.params.id, async (newId) => {
  await fetchPost(newId);
  await fetchComments(newId);
}, { immediate: true });
onMounted(() => {
  fetchPost(postId.value);
  fetchComments(postId.value)
});
provide('post', post)

</script>

<style scoped>
.comments {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}

</style>