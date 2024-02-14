<template>
<div class="navbar">
    <nav v-if="state.loggedIn">
          <RouterLink  to="/">
            <img src="@/assets/logo_green.png" alt="logo" class="navbar-logo" >
          </RouterLink>
          <nav class="navbar-links">
            <RouterLink :to="{name: 'PostWholeListView'}" class="nav-link">커뮤니티</RouterLink>
            <RouterLink :to="{name: 'ARCReport'}" class="nav-link">내 정보</RouterLink> 
            <div class="nav-link" @click="logout">로그아웃</div>
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

<script setup>
import { reactive, computed } from 'vue'
import { logout } from '@/api/outhApi';
import { useAuthStore } from '@/stores/auth';
const authStore = useAuthStore()
const state = reactive({
  loggedIn: authStore.isLogIn,
})

console.log(state)

</script>


<style scoped>
.navbar-links {
  margin-left: 45vw;
  display: flex;
  align-items: center;
  border-bottom: none;
  margin-right: -30vw;
  width: 150%;
}

nav {
  margin-bottom: -2vh;
}

.navbar {
  display: flex;
  align-items: center;
  /* justify-content: space-around; */
  margin-left: 7vw;
  margin-top: 2vh;
}

.nav-link {
  text-decoration: none;
  color: #333;
  border-bottom: 2px solid transparent;
  transition: border-color 0.3s;
  display: inline-block;
  margin-top: -26vh;
}


.nav-link:hover {
  border-bottom: 2px solid rgb(108, 159, 156);
  text-decoration: none;
  color: black;
  transition: 0.4s;
  padding-bottom: 10px;
}

</style>