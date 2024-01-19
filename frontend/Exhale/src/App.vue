<template>
  <header>
  <div>
    <RouterView/>
    <RouterLink :to="{ name: 'about-us' }">날숨은 무엇인가요?</RouterLink>
    <RouterLink :to="{ name: 'login' }">로그인</RouterLink>
    <RouterLink :to="{ name: 'signup' }">회원가입</RouterLink>
  </div>  
  </header>
  
</template>

<script setup>
import {onMounted, ref} from 'vue';
import axios from 'axios';
import {useRouter} from 'vue-router';
import { useCounterStore } from '@/stores/counter'; 
import AboutUs from '@/components/main/AboutUs.vue';
import Login from '@/components/auth/Login.vue';
import SignUp from '@/components/auth/SignUp.vue';
import { createRouter, createWebHistory } from 'vue-router';
const routes = [
{ path: '/about-us', name: 'about-us', component: AboutUs },
  { path: '/login', name: 'login', component: Login },
  { path: '/signin', name: 'signup', component: SignUp },
];
const router = createRouter({
  history: createWebHistory(),
  routes,
});


const app = createApp(App);
app.use(router);
app.mount('#app');
</script>

<style scoped>

</style>