  <template>
  
  <!-- <img src="@/assets/logo_white.png" class="intro-logo"> -->
  
  <button class="enlarge" @click="enlarge">{{ msg }}</button>
  <div class="p" :style="{ fontSize: fontSize + 'px' }">

    <div id="container" class="container" ref="container">
    <!-- FORM SECTION -->
    <form @submit.prevent="submitForm">
        <!-- SIGN UP -->
        <div class="col align-items-center flex-col sign-up">
          <div class="form-wrapper align-items-center">
            <div class="form sign-up">
              <div class="input-group">
                <i class='bx bxs-user'></i>
                <input class="input" v-model.trim="submitForm.userId" id="userId" placeholder="아이디" type="text"/>
              </div>
              <div class="input-group">
                <i class='bx bx-mail-send'></i>
                <input class="input" v-model.trim="submitForm.email" id="email" placeholder="이메일" type="email"/> 
              </div>
              <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <input class="input" v-model.trim="submitForm.fullname" id="fullname" placeholder="성명" type="text"/>
              </div>
              
              <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <input class="input" v-model.trim="submitForm.birthdate" id="birthdate" placeholder="생년월일" type="text"/>
              </div>

              <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <input class="input" v-model="submitForm.password" id="password" :type="passwordType" placeholder="비밀번호"/>
                <span @click="toggleVisibility('password')" class="eye-icon">
                <img src="@/assets/eye.png" alt="">
                </span>
              </div>
              
              <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <input v-model="submitForm.passwordConfirm" id="password-confirm" :type="passwordConfirmType" placeholder="비밀번호 확인"/>
                <span @click="toggleVisibility('password')" class="eye-icon">
                  <img src="@/assets/eye.png" alt="">
                </span>
              </div>

              <div class="input-group">
                <input v-model="submitForm.nickName" id="nickname" placeholder="닉네임" type="text"/>
              </div>

              <div class="submit1" >
                <button class="btn-bottom" type="submit" @click.prevent="submitForm()">
                  회원가입
                </button>
                <div class="kakaoLogin">
                  <button @click="snsLogin('?kakao')" class="kakao">
                    <img src="@/assets/kakao-logo.png" alt="signup">
                    카카오로 시작하기
                  </button>
                </div>
              </div>

              <p>
                <span>
                  이미 계정이 있으신가요?
                </span>
                <b @click="toggle" class="pointer">
                  로그인
                </b>
              </p>
          </div>
          </div>
        </div>
        <!-- END SIGN UP -->
      </form>
      <!-- END FORM SECTION -->


      <!-- CONTENT SECTION -->
      <div class="row content-row">
        <!-- SIGN UP CONTENT -->
        <div class="col align-items-center flex-col">
          <div class="text sign-up">
            <h2>Sign Up —</h2>
            <!-- <p>희망을 잃지 마세요!
              환영합니다! 4종 1,000개가 넘는 언어 재활 코스와 함께 일상으로의 복귀를 도와드리고 있어요. 함께 노력해봐요.
            </p> -->
          </div>
        </div>
        <!-- END SIGN UP CONTENT -->
      </div>
    </div>

</div>
</template>

<script setup>
import { reactive, ref, computed, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { RouterLink } from 'vue-router';
import { useAuthStore } from '@/stores/auth';
const store = useAuthStore()
const fontSize = ref(16);
const router = useRouter();
const userId = ref(null);
const email = ref(null);
const fullname = ref(null);
const birthdate = ref(null);
const password = ref(null);
const passwordConfirm = ref(null);
const nickName = ref(null)
const passwordType = ref("password");
const passwordConfirmType = ref("passwordConfirmType");

const submitForm = () => {
  const payload = {
    userId: userId.value,
    email: email.value,
    fullname: fullname.value,
    birthdate: birthdate.value,
    password: password.value,
    passwordConfirm: passwordConfirm.value,
    nickName: nickName.value
  }
  store.signup(payload)
};

const msg = computed(() => fontSize.value > 21 ? '원래대로' : '글자확대');
const enlarge = () => {
  fontSize.value ++;
  if (fontSize.value > 22) {
    fontSize.value = 16
  }
}

const toggleVisibility = (field) => {
  if (field === 'password') {
    passwordType.value = passwordType.value === 'password' ? 'text' : 'password';
  }}; 

const toggleVisibility1 = (field) => {
  if (field === 'password') {
    passwordType.value = passwordType.value === 'password' ? 'text' : 'password';
  }}; 
const toggleVisibility2 = (field) => {
  if (field === 'passwordConfirm') {
    passwordConfirmType.value = passwordConfirmType.value === 'password' ? 'text' : 'password';
  }
  };

const kakaoLogin = () => {
  axios.get('https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${64f53b3a322ebb16eabd9859392720c9}&redirect_uri=${http://localhost:5174/modals/LoginComplete}').then((res) => {
    router.push(res.data.auth_code_url)
  });
  return {kakaoLogin}};

const snsLogin = (type) => {
    window.location.href = `${store.API_URL}/api/user/login/${type}`;
};

const passwordInput = document.getElementById('password');

const container = ref(null);

const toggle = () => {
      router.push(`/login`)
  }

  // container ref가 mount 되었을 때 sign-up class를 추가합니다.
  onMounted(() => {
  // DOM이 마운트된 후에 `classList`에 접근합니다.
  setTimeout(() => {
    if (container.value) {
      container.value.classList.add('sign-up')
    }
  }, 300)
})


</script>

<style scoped>
  @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600&display=swap');
  @import "@/assets/scss/pages/_signup.scss";
</style>