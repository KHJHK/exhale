<template>
<div :style="{ fontSize: fontSize + 'px' }">
<div>
</div>
<div id="content">
    <section class="main-nav">
      <div class="navbar-logo-link">
        <RouterLink :to="{ name: 'MainPage' }">
          <img src="@/assets/logo_green.png" alt="logo" class="navbar-logo" >
        </RouterLink>
      </div>
      <div class="menu" @click="toggleMenu">
        <PostMenu v-if="!show" />
        <CommunityMenu v-else />
      </div>
    </section>

      <section class="sub-nav1">
        <div id="breadcrum">
          <RouterLink class="breadlink" :to="{name: 'MainPage'}">메인 홈</RouterLink>
          >
          <RouterLink class="breadlink" :to="{name: 'PostWholeListView'}">커뮤니티</RouterLink>
          >
          <RouterLink class="breadlink" :to="{name: 'PostWholeListView'}">전체</RouterLink>
        </div>
        <button class="enlarge" @click="enlarge" style="position: fixed; right: 0px; z-index: 10;">
        <img src="@/assets/plus.svg" class="plus">
        {{ msg }}
        </button> 
      </section>


      <svg-icon type="mdi" :path="path"></svg-icon>
      <PostSlider class="postslider" />

      <img src="@/assets/double-quote.svg" class="double-quote" >

      <div class="box-container">
        <h2>{{ post.title }}</h2>
        <h3>{{ post.nickname }}</h3>
        <p>{{ post.view }}</p>
        
        <p>{{ post.content }}</p>
        <p class="text-muted">{{ post.create_date}}</p>
      </div>


      <div id="buttonss">
        <button class="btn" @click="goListPage">
          <img src="@/assets/list.svg" class="list" >
          목록</button>
        <button class="btn" @click="goEditPage">
          <img src="@/assets/edit.svg" class="edit" >
          수정</button>
        <button class="btn" @click="remove" >
          <img src="@/assets/delete.svg" class="delete" >
          삭제</button>
      </div>
    
      <div class="comments">
        <CommentsCreate
        
        />
      </div>
      
      <!-- <PostEditView
        :post="post"
        /> -->
        <div class="commentlist">
          <CommentsList
          />
        </div>
  </div>
  
  <div class="arrows">
    <img src="@/assets/left-arrow.svg" class="left-arrow" @click="navigateToPost(-1)">
    <img src="@/assets/right-arrow.svg" class="right-arrow" @click="navigateToPost(1)">
  </div>
  
  <footer class="footer">
    <Footers/>
  </footer>
</div>
</template>

<script setup>

import { ref, onMounted, watch, provide, reactive, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { defineProps } from 'vue';
import CommentsCreate from '@/components/comments/CommentsCreate.vue';
import CommentsList from '@/components/comments/CommentsList.vue';
import { boardDetail, boardList, deletePost } from '@/api/boards';
import CommunityMenu from '@/components/modals/CommunityMenu.vue';
import PostItem from '@/components/posts/PostItem.vue';
import PostMenu from '@/components/posts/PostMenu.vue';
import PostSlider from '@/components/posts/PostSlider.vue';
import Footers from '@/components/common/Footers.vue';
import { useCrudStore } from '@/stores/crud';
import { useAuthStore } from '@/stores/auth';
import { mdiPrinterPosEditOutline } from '@mdi/js';
import { storeToRefs } from 'pinia'
import PostEditView from './PostEditView.vue';
import axios from 'axios';
const crud = useCrudStore()
const {posts} = storeToRefs(crud)
const store = useAuthStore()
const token = store.jwtToken
const router = useRouter()
const route = useRoute()
const postId = route.params.id
//url의 id앞에 콜론 제거
// postId.value = postId.value.slice(1)


// const post = ref({
//     id : response.value.id,
//     content: response.value.content,
//     create_date: response.value.create_date,
//     view:response.value.view,
//     nickname:response.value.nickname,
//     title: response.value.title
// })

const post = ref({})

const show = ref(false)
function toggleMenu() {
  show.value = !show.value
}

const fontSize = ref(16);
const msg = computed(() => fontSize.value > 21 ? '원래대로' : '글자확대');
const enlarge = () => {
  fontSize.value ++;
  if (fontSize.value > 22) {
    fontSize.value = 16
  };
};

// 페이지 렌더링 시 detail요청으로 바로 내용 받아오기
onMounted(async () => {
  await boardDetail(route.params.id).then((res) => {
    post.value = res.data.response
  })

});

// const fetchPost = async (postId) => {
//   try {
//     const { data } = await getPostById(postId)
  
//     post.value = data
//     setPost(data)
//   } catch (error) {
//     console.error(error);
//   }  
// };

// const setPost = ({title, content, create_date }) => {
//     post.value.title = title
//     post.value.content = content
//     post.value.create_date = create_date
// }


const remove = async () => {
   
    try {
        if (confirm('삭제 하시겠습니까?') === false) {
            return
        }
        await deletePost(postId, token)
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
  const currentIndex = posts.value.findIndex((p) => p.id === postId);
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
  const id = post.value.id || postId
  router.push({ name: 'PostEditView', params: { postId } })
}

// watch(() => route.params.id, async (newId) => {
//   await fetchPost(newId);
//   await fetchComments(newId);
// }, { immediate: true });
// onMounted(() => {
//   fetchPost(postId.value);
//   fetchComments(postId.value)
// });
// provide('post', post)

</script>

<style lang="scss" scoped>
@import "@/assets/scss/pages/_postdetail.scss";
@import "@/assets/scss/layout/_article.scss";
</style>