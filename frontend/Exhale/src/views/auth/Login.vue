<template>
  <button
    class="enlarge"
    @click="enlarge"
    style="position: fixed; right: 0px; z-index: 10"
  >
    <img src="@/assets/plus.svg" class="plus" />
    {{ msg }}
  </button>

  <div :style="{ fontSize: fontSize + 'px' }">
    <div id="container" class="container" ref="container">
      <!-- FORM SECTION -->
      <form @submit.prevent="submit">
        <!-- <p>날;숨입니다. 여러분의 회복을 기원합니다.</p> -->
        <!-- SIGN IN -->
        <div class="col align-items-center flex-col sign-in">
          <img src="@/assets/logo_white.png" class="intro-logo" />
          <div class="form-wrapper align-items-center">
            <div class="form sign-in">
              <div class="input-group">
                <i class="bx bxs-user"></i>
                <input
                  class="input"
                  type="text"
                  v-model="userId"
                  placeholder="아이디"
                />
              </div>

              <div class="input-group">
                <i class="bx bxs-lock-alt"></i>
                <input
                  class="input"
                  v-model="password"
                  :type="passwordType"
                  placeholder="비밀번호"
                />
                <span @click="toggleVisibility('password')" class="eye-icon">
                  <img src="@/assets/eye.png" alt="" />
                </span>
              </div>

              <button class="Btn" type="submit" @click.prevent="log_In">
                로그인
              </button>

              <button @click="kakao" class="kakao">
                <img src="@/assets/kakao-logo.png" alt="" />&nbsp;
                <label>카카오로 시작하기</label>
              </button>

              <p>
                <b>
                  <RouterLink to="forgot-id" class="forgot"
                    >아이디 찾기</RouterLink
                  >
                  /
                  <RouterLink to="forgot-password" class="forgot"
                    >비밀번호 찾기</RouterLink
                  >
                </b>
              </p>

              <p>
                <span> 새로 오셨나요? </span>
                <b @click="toggle" class="pointer"> 회원가입 </b>
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
            <!-- <h2>
          Log In
        </h2> -->
            <!-- <p>로그인 하고 완치를 향해 나아가보세요.</p>
        <p>누구나 극복할 수 있습니다!</p> -->
          </div>
          <div class="img sign-in"></div>
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
import { useAuthStore} from '@/stores/auth'; 


import { RouterLink } from 'vue-router';
import { useRouter } from 'vue-router';
import { logIn } from '@/api/outhApi';
  // import { kakao } from '@/api/outhApi';
  const router = useRouter()
  const fontSize = ref(16);
  const passwordType = ref("password");
  const password = ref('');
  const userId = ref('')




const log_In = async () => {
  const response = await logIn(
    userId.value,
    password.value
  )
  router.push('/mainpage')
  
} 

////////////////////////////////////////////////////////
const toggle = () => {
  router.push({ name: "SignUp" });
};

const container = ref(null);

// container ref가 mount 되었을 때 sign-in class 추가
onMounted(() => {
  setTimeout(() => {
    if (container.value) {
      container.value.classList.add("sign-in");
    }
  }, 300);
});
////////////////////////////////////////////////////////

const authStore = useAuthStore();

const toggleVisibility = (field) => {
  if (field === "password") {
    passwordType.value =
      passwordType.value === "password" ? "text" : "password";
  }
};

const msg = computed(() => (fontSize.value >= 28 ? "원래대로" : "글자확대"));
const enlarge = () => {
  fontSize.value += 4;
  if (fontSize.value >= 32) {
    fontSize.value = 16;
  }
};

const created = () => {
  const code = new URLSearchParams(window.location.search).get("code");
  if (code) {
    console.log("인가 코드:", code);
  }
};
</script>

<style lang="scss" scoped>
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600&display=swap");
@import "@/assets/scss/pages/_login.scss";
</style>
