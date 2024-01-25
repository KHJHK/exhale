<template>
<div class="navbar">
    <nav>
          <RouterLink  :to="{ name: 'MainPage' }">
            <img src="@/assets/logo_green.png" alt="logo" class="navbar-logo" >
          </RouterLink>
        <nav class="navbar-links">
          <RouterLink  :to="{ name: 'AboutUs' }"  class="nav-link">날숨은 무엇인가요?</RouterLink>
          <RouterLink  :to="{ name: 'Login' }" class="nav-link">로그인</RouterLink>
          <RouterLink  :to="{ name: 'SignUp' }" class="nav-link">회원가입</RouterLink>
          </nav>
    </nav>
    <!-- <nav v-if="store.isLogIn">
          <RouterLink :to="{name: 'ReadPost'}" class="nav-link">복습하기</RouterLink> 
          <RouterLink :to="{name: 'ReadPost'}" class="nav-link">커뮤니티</RouterLink>
          <RouterLink :to="{name: 'ReadPost'}" class="nav-link">내 정보</RouterLink> 
          <RouterLink :to="{name: 'ReadPost'}" class="nav-link">로그아웃</RouterLink> 
          </nav> -->
  </div> 

 
</template>

<script setup>
import { useAuthStore } from '@/stores/auth';
import { useCounterStore } from '@/stores/counter';

const store = useCounterStore()
</script>

<style scoped>
/* nav {
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.container {
  margin-right: 20px;

}
.logo {
  width: 80px;
  height: auto;
  margin-right: 20px;
}
nav a {
  
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
  margin-top: 1px;
  justify-content: end;
  
  
}
nav a:first-of-type {
  border: 0;
}

.contents {
  border: 0;
}

.container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  
}

.logo {
  position : static ;
}

img {
  width: 20px;
  height: auto;
}
.eye {
  border: white 1px solid;
}
.form-wrap {
    margin-left: 700px;
    margin-top: 100px;
}
.p {
  margin-left: 150px;
  margin-top: 100px;
}
.enlarge {
  margin-left: 1000px;
} */


.navbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  /* padding: 0rem 1rem; */
  
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
  border-bottom: 2px solid #19691C; /* 호버 시 밑줄이 나타납니다 */
  text-decoration: none;
  color: black;
  transition: 0.4s;
  padding: 3px;
}

</style>
<style>
.navbar-logo {
  width: 100px; 
  /* margin-right: 800px;  */
  display: grid;
  grid-template-columns: 1fr auto 1fr;
}
</style>