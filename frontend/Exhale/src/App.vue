<template>
  <header>
  <div>
    <nav>
      <RouterLink :to="{name: 'AboutUs'}">날숨은 무엇인가요?</RouterLink>
      <RouterLink :to="{name: 'ReadPost'}">커뮤니티</RouterLink>
      <RouterLink :to="{name: 'Login'}">로그인</RouterLink>
      <RouterLink :to="{name: 'SignUp'}">회원가입</RouterLink>

    </nav>
  </div>  
  <RouterView/>
  </header>

</template>

<script setup>
// import {createApp, onMounted, ref} from 'vue';

// import axios from 'axios';
// import {useRouter} from 'vue-router';
// import { useCounterStore } from '@/stores/counter'; 

// import { createRouter, createWebHistory } from 'vue-router';



// const routes = [
//   { path: '/about-us', name: 'about-us', component: AboutUs },
//   { path: '/login', name: 'login', component: Login },
//   { path: '/signin', name: 'signup', component: SignUp },
// ];



</script>

<style scoped>
nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}
nav a:first-of-type {
  border: 0;
}

</style>