<template>
  <button class="enlarge" @click="enlarge">{{ msg }}</button>
  <div class="p" :style="{ fontSize: fontSize + 'px' }">

    <div id="container" class="container" ref="container">
    <!-- FORM SECTION -->
    <form @submit.prevent="submitLogin">
      <!-- <p>날;숨입니다. 여러분의 회복을 기원합니다.</p> -->
      <!-- SIGN IN -->
      <div class="col align-items-center flex-col sign-in">
        <img src="@/assets/logo_white.png" class="intro-logo">
        <div class="form-wrapper align-items-center">
          <div class="form sign-in">
            
            <div class="input-group">
              <i class='bx bxs-user'></i>
              <input class="input" type="text" v-model="loginForm.userId" placeholder="아이디">
            </div>
            
            <div class="input-group">
              <i class='bx bxs-lock-alt'></i>
              <input class="input" type="password" v-model="loginForm.password" placeholder="비밀번호">
              <span @click="toggleVisibility('password')" class="eye-icon">
                <img src="@/assets/eye.png" alt="">
              </span>
            </div>
            
            
            <button class="loginBtn" type="submit">
              로그인
            </button>

            <button @click="snsLogin('?kakao')" class="kakao">
              <img src="@/assets/kakao-logo.png" alt="login">&nbsp;
              <label>카카오로 시작하기</label>
            </button>
          
            <p>
              <b>
                <RouterLink to="forgot-id" class="forgot">아이디 찾기</RouterLink> /
                <RouterLink to="forgot-password" class="forgot">비밀번호 찾기</RouterLink>
              </b>
            </p>
            
            <p>
              <span>
                새로 오셨나요?
              </span>
              <b @click="toggle" class="pointer">
                회원가입
              </b>
            </p>
          </div>
        </div>
      </div>
      <!-- END SIGN IN -->
      </form>
    <!-- END FORM SECTION -->


    <!-- CONTENT SECTION -->
    <div class="row content-row">
      <!-- SIGN IN CONTENT -->
      <div class="col align-items-center flex-col">
      
      <div class="text sign-in">
        <h2>
          Log In
        </h2>
        <!-- <p>로그인 하고 완치를 향해 나아가보세요.</p>
        <p>누구나 극복할 수 있습니다!</p> -->
      </div>
        <div class="img sign-in">
    
        </div>
      </div>
      <!-- END SIGN IN CONTENT -->
      
    </div>
    <!-- END CONTENT SECTION -->
  </div>

</div>

</template>
  
  <script setup>
  import { reactive, ref, computed, onMounted } from 'vue';
  import axios from 'axios';
  import { useAuthStore } from '@/stores/auth'; 
  import { RouterLink } from 'vue-router';
  import { useRouter } from 'vue-router';

  const router = useRouter()
  ////////////////////////////////////////////////////
  // let container = document.getElementById('container')

  // toggle = () => {
  //   container.classList.toggle('sign-in')
  //   container.classList.toggle('sign-up')
  // }



  // 컴포넌트의 root element를 찾기 위한 ref를 생성합니다.
  const container = ref(null);

  // toggle 함수를 선언합니다. 이 함수는 container의 classList를 조작합니다.
  // const toggle = () => {
  //   if (container.value) {
  //     container.value.classList.toggle('sign-in');
  //     container.value.classList.toggle('sign-up');
  //   }
  // };


  const toggle = () => {
      router.push(`/signup`)
  }
  

  // container ref가 mount 되었을 때 sign-in class를 추가합니다.
  onMounted(() => {
  // DOM이 마운트된 후에 `classList`에 접근합니다.
  setTimeout(() => {
    if (container.value) {
      container.value.classList.add('sign-in')
    }
  }, 300)
})
  //////////////////////////////////////////////////

  const authStore = useAuthStore(); 
  const loginForm = reactive({
    userId: '',
    password: ''
  });
  const fontSize = ref(16);

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
  
  </script>
  
  <style scoped>
  @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600&display=swap');
  @import "@/assets/scss/pages/_login.scss";
  </style>