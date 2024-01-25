  <template>
        <button class="enlarge" @click="enlarge">{{ msg }}</button>    
      <div class="p" :style="{ fontSize: fontSize + 'px' }">
        <h1>회원가입</h1>
        <p>희망을 잃지 마세요!
          환영합니다! 4종 1,000개가 넘는 언어 재활 코스와 함께 일상으로의 복귀를 도와드리고 있어요. 함께 노력해봐요.
        </p>
        </div>   
            <div class="form-wrap">
              <form @submit.prevent="submitform">
                <div class="input-with-label">
                  <input v-model.trim="userId" id="userId" placeholder="아이디" type="text" />
                  
                </div>
                <div class="input-with-label">
                  <input v-model.trim="email" id="email" placeholder="이메일" type="text" />
                  
                </div>
                <div class="input-with-label">
                  <input v-model.trim="fullname" id="fullname" placeholder="성명" type="text" />
                  
                </div>
                <div class="input-with-label">
                  <input v-model.trim="birthdate" id="birthdate" placeholder="생년월일" type="text" />
                  
                </div>
                <div class="input-with-label">
                  <input v-model="password" id="password" :type="passwordType" placeholder="비밀번호" />
                  <button @click="toggleVisibility('passwordConfirm')" class="eye">
                    <img src="@/assets/eye.png" alt="eye-icon" >
                  </button>
                </div>
                <div class="input-with-label">
                  <input v-model="passwordConfirm" id="password-confirm" :type="passwordConfirmType" placeholder="비밀번호 확인" />
                  <button @click="Visibility" class="eye">
                    <img src="@/assets/eye.png" alt="eye-icon" >
                  </button>
                </div>
                <div class="input-with-label">
                  <input v-model="nickName" id="nickname" placeholder="닉네임" type="text" />
                  
                </div>
                <button class="btn-bottom" style="width: 170px;" type="submit">회원가입</button> 
                <br>
                
                <v-card-actions style="justify-content: center">
                  <button @click="snsLogin('kakao')" style="width: 170px;">
                    <img src="@/assets/kakao_login.png" alt="login" style="width: 150px;">
                  </button>
                </v-card-actions>
                
                
              </form>
              </div>
<<<<<<< HEAD
              
            </template>
<script setup>
=======
              <div class="input-with-label">
                <input v-model="nickName" id="nickname" placeholder="생년월일" type="text" />
                
              </div>
              <div class="input-with-label">
                <input v-model="password" id="password" :type="passwordType" placeholder="비밀번호" />
                <button @click="Visibility" class="eye">
                  <img src="@/assets/eye.png" alt="eye-icon" >
                </button>
              </div>
              <div class="input-with-label">
                <input v-model="passwordConfirm" id="password-confirm" :type="passwordConfirmType" placeholder="비밀번호 확인" />
                <button @click="Visibility" class="eye">
                  <img src="@/assets/eye.png" alt="eye-icon" >
                </button>
              </div>
              <div class="input-with-label">
                <input v-model="nickName" id="nickname" placeholder="닉네임" type="text" />
                
              </div>
              <button class="btn-bottom" style="width: 170px;" type="submit">회원가입</button> 
              <br>
              <button class="social-sign" style="width: 170px;" type="submit">카카오톡 1초 회원가입</button>
            </div>
            
  </template>
<!-- <script setup>
>>>>>>> feature/S10P12B208-194_커뮤니티페이지_화면설계
import { ref, computed } from 'vue';
import { onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
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
<<<<<<< HEAD
  store.signup(payload)
};
const msg = computed(() => fontSize.value > 21 ? '원래대로' : '글자확대');
const enlarge = () => {
  fontSize.value ++;
  if (fontSize.value > 22) {
    fontSize.value = 16
  };
};

const toggleVisibility = (field) => {
  if (field === 'password') {
    passwordType.value = passwordType.value === 'password' ? 'text' : 'password';
  } else if (field === 'passwordConfirm') {
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
</script>

 
  <!-- <script>
 
=======
});
</script> -->

 
  <script>
  import { ref } from 'vue';
  const passwordInput = document.getElementById('password');
>>>>>>> feature/S10P12B208-194_커뮤니티페이지_화면설계
  export default {
    data: () => {
        return {
            fontSize: 16,
            email: "",
            password: "",
            passwordConfirm: "",
            nickName: "",
            isTerm: false,
            isLoading: false,
            error: {
                email: false,
                password: false,
                nickName: false,
                passwordConfirm: false,
                term: false
            },
            isSubmit: false,
            passwordType: "password",
            passwordConfirmType: "passwordConfirmType",
            termPopup: false
        };
    },
    methods: {
<<<<<<< HEAD
        submitform() {
            console.log('회원가입완료');
        },
        Visibility() {
            this.passwordType = this.text;
        },
    },
   
}
  </script> -->
=======
      submitform() {
        console.log('회원가입완료')
      },
      Visibility() {
        this.passwordType = this.text },
        
      }
    }
  </script>
>>>>>>> feature/S10P12B208-194_커뮤니티페이지_화면설계
  <style scoped>
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
}
</style>