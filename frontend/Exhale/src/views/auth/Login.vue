<template>
  <button class="enlarge" @click="enlarge">{{ msg }}</button>   
   <div class="p" :style="{ fontSize: fontSize + 'px' }">
    <div class="Flexbox">
      <div class="msg">
        <h1>로그인</h1>
        <p>로그인 하고 완치를 향해 나아가보세요.</p>
        <p>누구나 극복할 수 있습니다!</p>
      </div>
      <div class="loginform">
        <div>날;숨입니다. 여러분의 회복을 기원합니다.</div>
      </div>
      <form @submit.prevent="submitLogin">
        <div>
          <input class="input" type="text" v-model="loginForm.userId" placeholder="아이디">
          
          <br>  
          <input class="input" type="password" v-model="loginForm.password" placeholder="비밀번호">
          <button @click="toggleVisibility('password')" class="eye">
          <img src="@/assets/eye.png" alt="eye-icon" >
          </button>
          <br>
          <button class="loginBtn" type="submit" style=" width:185px">로그인</button>
          <br>
          <div class="contents">
            <button @click="snsLogin('?kakao')" style="margin-top: 20px; width: 170px; border: 0px; background-color: white;">
              <img src="@/assets/kakao_login.png" alt="login" style="width: 150px;">
              </button>
              <div class="links">
                <RouterLink to="forgot-id">아이디 찾기</RouterLink> /
                <RouterLink to="forget-password">비밀번호 찾기</RouterLink>
                <br>
                새로 오셨나요?<RouterLink to="signup">회원가입</RouterLink>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
    </template>
  
  <script setup>
  import { reactive, ref, computed } from 'vue';
  import axios from 'axios';
  import { useAuthStore } from '@/stores/auth'; 
  import { RouterLink } from 'vue-router';
 
  
  const authStore = useAuthStore(); 
  const loginForm = reactive({
    userId: '',
    password: ''
  });
  const fontSize = ref(16);
  // 로그인 요청
  // const submitLogin = async () => {
  //   try {
  //     const response = await axios.post('http://주소.com/api/login', {
  //       username: loginForm.userId,
  //       password: loginForm.password
  //     });
  //     // 로그인이 성공적으로 이루어진 후의 처리 로직
  //     authStore.setUser(response.data);
  //     console.log('로그인 성공:', response.data);
  //   } catch (error) {
  //     console.error('로그인 실패:', error);
  //   }
  // };
  
  const toggleVisibility = (field) => {
  if (field === 'password') {
    passwordType.value = passwordType.value === 'password' ? 'text' : 'password';
  }}; 

const msg = computed(() => fontSize.value > 21 ? '원래대로' : '글자확대');
const enlarge = () => {
  fontSize.value ++;
  if (fontSize.value > 22) {
    fontSize.value = 16
  };
};

const created = () => {
    const code = new URLSearchParams(window.location.search).get('code');
    if (code) {
      
      console.log("인가 코드:", code);
    }
}

const login = (userId, password) => {
  axios ({
    method: 'post',
    url: 'api/general/login',
    headers: {
      "Content-Type":"multipart/form-data"
    },
    data: {
      login_id: userId,
      password: password,
      
    }
  })
}

const kakaoLogin = () => {
  axios ({
    method: 'post',
    url: 'api.outh/kakao/login',
    headers: {
      "Content-Type":"application/json"
    },
    data: {

    }
  })

}
  </script>
  
<style scoped>

  /* .msg {
      margin-left: 200px;
  } */
  
  /* .loginform {
      margin-left : 700px;
      margin-top: -100px;
  } */

  /* .contents{
    display: flex;
    justify-content: center;

  } */

.loginBtn {
  border-radius: 10px;
  border: 2px solid lightgray;
  background: lightgray;
}

.input {
  border: rgb(108, 159, 156) solid 2px;
  border-radius: 10px;
  margin-bottom: 10px;
}

.Flexbox {
  display: flex;
  flex-direction: column;
  /* justify-content: center; */
  align-items: center; 
  height: 100vh;
  
}

.p {
  margin-left: 150px;
  margin-top: 100px;
}

.enlarge {
  margin-left: 89vw;
  width: 100px;
  border-radius: 20px;
  border: rgb(108, 159, 156) solid 2px;
  background-color: white;
  
}
.eye {
  position: absolute;
  margin-left: 140px;
  margin-top: 13px;
  transform: translateY(-50%);
  border: none;
  background: transparent;
  cursor: pointer;
}
img {
  width: 20px;
  height: auto;
}
</style>