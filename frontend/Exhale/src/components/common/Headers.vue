<template>
<div class="navbar">
    <nav v-if="state.loggedIn">
          <RouterLink  to="/">
            <img src="@/assets/logo_green.png" alt="logo" class="navbar-logo" >
          </RouterLink>
          <nav class="navbar-links">
            <RouterLink :to="{name: 'Review'}" class="nav-link">복습하기</RouterLink> 
            <RouterLink :to="{name: 'PostWholeListView'}" class="nav-link">커뮤니티</RouterLink>
            <RouterLink :to="{name: 'ARCReport'}" class="nav-link">내 정보</RouterLink> 
            <div class="nav-link" @click="logOut">로그아웃</div>
          </nav>
    </nav>
    <nav v-else>
          <RouterLink to="/">
            <img src="@/assets/logo_green.png" alt="logo" class="navbar-logo" >
          </RouterLink>
          <nav class="navbar-links">
          <RouterLink  :to="{ name: 'AboutUs' }"  class="nav-link">날숨은 무엇인가요?</RouterLink>
          <RouterLink  :to="{ name: 'Login' }" class="nav-link">로그인</RouterLink>
          <RouterLink  :to="{ name: 'SignUp' }" class="nav-link">회원가입</RouterLink>
          </nav>
    </nav>
  </div> 

 
</template>

<script>
import { useAuthStore } from '@/stores/auth';
import { useCounterStore } from '@/stores/counter';
import { reactive } from 'vue';
export default {
  setup() {
    const state = reactive({
      loggedIn: false,
    });
    
    return {state};
  }
}

</script>

<style scoped>
.navbar-links {
  margin-left: 60vw;
}

.navbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  
}
.navbar > nav {
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
  transition: border-color 0.3s; /* Smooth transition for hover effect */
  display: inline-block;
}


.nav-link:hover {
  border-bottom: 2px solid rgb(108, 159, 156); /* 호버 시 밑줄이 나타납니다 */
  text-decoration: none;
  color: black;
  transition: 0.4s;
  padding: 3px;
}

</style>


<style>
.navbar-logo {
 width: 120px; 
 margin-left: 4vw;
 top: 10px;
}
</style>