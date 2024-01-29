<template>
  <PostMenu />

  <br>
  
  <div id="breadcrum">
      <RouterLink :to="{name: 'MainPage'}">메인 홈</RouterLink>
      >
      <RouterLink :to="{name: 'PostWholeListView'}">커뮤니티</RouterLink>
      >
      <RouterLink :to="{name: 'PostStoryListView'}">환자이야기</RouterLink>
  </div>

  <br>

  <h3>전체 글</h3>
  <nav class="navbar-links">
      <RouterLink 
        :to="{name: 'PostWholeListView'}" 
        class="nav-link"
        :class="{ active: route.name === 'PostWholeListView'}"
        active-class="active"
      >전체</RouterLink>
      <RouterLink 
        :to="{name: 'PostInfoListView'}" 
        class="nav-link"
        :class="{ active: route.name === 'PostInfoListView' }"
        active-class="active"
        >정보 글</RouterLink>
      <RouterLink 
        :to="{name: 'PostReviewListView'}" 
        class="nav-link"
        :class="{ active: route.name === 'PostReviewListView' }"
        active-class="active"
        >치료 후기</RouterLink>
      <RouterLink 
        :to="{name: 'PostStoryListView'}" 
        class="nav-link"
        :class="{ active: route.name === 'PostStoryListView' }"
        active-class="active"
      >환자 이야기</RouterLink>
  </nav>

  <PostSlider />

  <br>

  <PostCreateBtn /> <PostSearch />

  <hr>

  <hr class="my-4" />
  <RouterView></RouterView>

</template>

<script setup>
import { useRoute } from 'vue-router'
import PostMenu from '@/components/posts/PostMenu.vue'
import PostSlider from '@/components/posts/PostSlider.vue'
import PostSearch from '@/components/posts/PostSearch.vue'
import PostCreateBtn from '@/components/posts/PostCreateBtn.vue'
const route = useRoute()
</script>

<style lang="scss" scoped>
#breadcrum a {
  color: black;
}
.nav {
display: flex;
align-items: center;

width: 100%
}

nav a:first-of-type {
gap: 80px;
}

.navbar-links {
display: flex;
align-items: center;
gap: 50px; 
}

.nav-link {
text-decoration: none;
color: #333; /* Text color */
padding-bottom: 0.5rem; /* Space for the underline */
border-bottom: 2px solid transparent; /* Invisible underline */
display: inline-block;
transition: color 0.3s ease, border-bottom 0.3s ease;
}


.nav-link.active {
  border-bottom: 2px solid rgb(108, 158, 156);
  color: black;
  padding: 3px;
  transition: color 0.3s ease, border-bottom 0.3s ease;
}

</style>
