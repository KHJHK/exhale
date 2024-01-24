  <template>
        <button class="enlarge" @click="enlarge">{{ msg }}</button>    
      <div class="p" :style="{ fontSize: fontSize + 'px' }">
        <h1>회원가입</h1>
        <p>희망을 잃지 마세요!
          환영합니다! 4종 1,000개가 넘는 언어 재활 코스와 함께 일상으로의 복귀를 도와드리고 있어요. 함께 노력해봐요.
        </p>
        </div>   
            <div class="form-wrap">
              <form @submit.prevent="submitform"></form>
              <div class="input-with-label">
                <input v-model="nickName" id="nickname" placeholder="아이디" type="text" />
                
              </div>
              <div class="input-with-label">
                <input v-model="email" id="email" placeholder="이메일" type="text" />
                
              </div>
              <div class="input-with-label">
                <input v-model="nickName" id="nickname" placeholder="성명" type="text" />
                
              </div>
              <div class="input-with-label">
                <input v-model="nickName" id="nickname" placeholder="생년월일" type="text" />
                
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
              <button class="social-sign" style="width: 170px;" type="submit">카카오톡 1초 회원가입</button>
            </div>
            
  </template>
<script setup>
import { ref, computed } from 'vue';
import { onMounted } from 'vue';

const fontSize = ref(16);
const email = ref("");
const password = ref("");
const passwordConfirm = ref("");
const nickName = ref("");
const isTerm = ref(false);
const isLoading = ref(false);
const error = ref({
  email: false,
  password: false,
  nickName: false,
  passwordConfirm: false,
  term: false
});
const isSubmit = ref(false);
const passwordType = ref("password");
const passwordConfirmType = ref("passwordConfirmType");
const termPopup = ref(false);

const submitForm = () => {
  console.log('회원가입완료');
};
const msg = computed(() => fontSize.value > 22 ? '글자축소' : '글자확대');
// enlarge 메서드 추가
const enlarge = () => {
  fontSize.value ++;
};

const toggleVisibility = (field) => {
  if (field === 'password') {
    passwordType.value = passwordType.value === 'password' ? 'text' : 'password';
  } else if (field === 'passwordConfirm') {
    passwordConfirmType.value = passwordConfirmType.value === 'password' ? 'text' : 'password';
  }
  };

const passwordInput = document.getElementById('password');

onMounted(() => {
  if (passwordInput.value) {
   
  }
});
</script>

 
  <!-- <script>
 
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
        submitform() {
            console.log('회원가입완료');
        },
        Visibility() {
            this.passwordType = this.text;
        },
    },
   
}
  </script> -->
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