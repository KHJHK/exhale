<template>
    <div class="msg">
      <h1>로그인</h1>
      <p>로그인 하고 완치를 향해 나아가보세요.</p>
      <p>누구나 극복할 수 있습니다!</p>
    </div>
    <div class="loginform">
      <div>날;숨입니다. 여러분의 회복을 기원합니다.</div>
      <form @submit.prevent="submitLogin">
        <div>
            <input type="text" v-model="loginForm.userId" placeholder="아이디">
            <br>  
            <input type="password" v-model="loginForm.password" placeholder="비밀번호">
            <br>
            <button type="submit" style=" width:185px">로그인</button>
            <div class="contents">
              <button @click="snsLogin('?kakao')" style="margin-top: 20px; width: 170px; border: 0px; background-color: white;">
                <img src="@/assets/kakao_login.png" alt="login" style="width: 150px;">
              </button>
              <RouterLink to="forgot-id">아이디 찾기</RouterLink> /
              <RouterLink to="forget-password">비밀번호 찾기</RouterLink>
            </div>
        </div>
       
    </form>
    </div>
  </template>
  
  <script setup>
  import { reactive } from 'vue';
  import axios from 'axios';
  import { useAuthStore } from '@/stores/auth'; 
  import { RouterLink } from 'vue-router';
  const authStore = useAuthStore(); 
  const loginForm = reactive({
    userId: '',
    password: ''
  });
  
  // 로그인 요청
  const submitLogin = async () => {
    try {
      const response = await axios.post('http://주소.com/api/login', {
        username: loginForm.userId,
        password: loginForm.password
      });
      // 로그인이 성공적으로 이루어진 후의 처리 로직
      authStore.setUser(response.data);
      console.log('로그인 성공:', response.data);
    } catch (error) {
      console.error('로그인 실패:', error);
    }
  };
  
  </script>
  
  <style scoped>

  .msg {
      margin-left: 200px;
  }
  
  .loginform {
      margin-left : 700px;
      margin-top: -100px;
  }

  .contents{
    display: flex;
    
   justify-content: center;
   
  }
  </style>
  