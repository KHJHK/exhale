  <template>
  
  <!-- <img src="@/assets/logo_white.png" class="intro-logo"> -->
  
  <button class="enlarge" @click="enlarge">{{ msg }}</button>
  <div class="p" :style="{ fontSize: fontSize + 'px' }">

    <div id="container" class="container" ref="container">
    <!-- FORM SECTION -->
    <form>
        <!-- SIGN UP -->
        <div class="col align-items-center flex-col sign-up">
          <img src="@/assets/logo_white.png" class="intro-logo">
          <div class="form-wrapper align-items-center">
            <div class="form sign-up">
              <div class="input-group">
                <i class='bx bxs-user'></i>
                <input class="input" v-model.trim="userId" id="userId" placeholder="아이디" type="text"/>
                <button @click="isIdDuplicated">중복확인</button>
              </div>
              <div class="input-group">
                <i class='bx bx-mail-send'></i>
                <input class="input" v-model.trim="email" id="email" placeholder="이메일" type="email"/> 
              </div>
              <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <input class="input" v-model.trim="fullname" id="fullname" placeholder="성명" type="text"/>
              </div>
              
              <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <input class="input" v-model.trim="birthdate" id="birthdate" placeholder="생년월일" type="text"/>
              </div>

              <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <input class="input" v-model="password" id="password" :type="passwordType" placeholder="비밀번호"/>
                <span @click="toggleVisibility('password')" class="eye-icon">
                <img src="@/assets/eye.png" alt="">
                </span>
              </div>
              
              <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <input v-model="passwordConfirm" id="password-confirm" :type="passwordConfirmType" placeholder="비밀번호 확인"/>
                <span @click="toggleVisibility('password')" class="eye-icon">
                  <img src="@/assets/eye.png" alt="">
                </span>
              </div>

              <div class="input-group">
                <input v-model="nickName" id="nickname" placeholder="닉네임" type="text"/>
              </div>

              <div class="submit1" >
                <button class="btn-bottom" type="submit" @click.prevent="sign_up">
                  회원가입
                </button>
                <div class="kakaoLogin">
                  <button @click="kakaoLogin" class="kakao">
                    <img src="@/assets/kakao-logo.png" alt="signup">&nbsp;
                    <label>카카오로 시작하기</label>
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
            <h2>Sign Up</h2>
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
import { reactive, ref, computed, onMounted, watch } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { RouterLink } from 'vue-router';
import { useAuthStore } from '@/stores/auth';
import { isIdDuplicated, signUp, kakaoLogin } from '@/api/outhApi.js';



const store = useAuthStore()
const fontSize = ref(16);
const router = useRouter();
const userId = ref('');
const email = ref('');
const fullname = ref('');
const birthdate = ref('');
const password = ref('');
const passwordConfirm = ref('');
const nickName = ref('')
const passwordType = ref("password");
const passwordConfirmType = ref("passwordConfirmType");

const valid = ref({
  userId: false,
  email: false,
  fullname: false,
  birthdate: false,
  password: false,
  nickName: false
});

const userIdErr = ref(false);
const emailErr = ref(false);
const fullnameErr = ref(false);
const birthdateErr = ref(false);
const passwordErr = ref(false);
const nickNameErr = ref(false);

watch(userId, (newValue) => {
  userIdErr.value = !newValue.trim()  
  if (userId.value < 2) {
    userIdErr.value = true
  }
  check_userId();   
})

watch(email, (newValue) => {
  check_email();
})

watch(fullname, (newValue) => {
  check_fullname()
})

watch(birthdate, (newValue) => {
  check_birthdate()
})

watch(passwordConfirm, (newValue) => {
  if (passwordConfirm.value !== newValue)
  passwordErr.value = true
  check_password()
})

function check_userId() {
  valid.value.userId = !userIdErr.value;

}

function check_email() {
  const emailValid = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  emailErr.value = !emailValid.test(email.value);
  valid.value.email = !emailErr.value;
}

function check_fullname() {
  if (!fullname.value.trim()) {
    fullnameErr.value = true;
  } else {
    valid.value.fullname = !fullnameErr.value;
  }
}

function check_birthdate() {
  valid.value.birthdate = !birthdateErr.value;
}

function check_password() {
  valid.value.password = !passwordErr.value
}




const sign_up = async () => {
  if (
    !userId.value ||
    !password.value ||
    !passwordConfirm.value ||
    !email.value ||
    !fullname.value ||
    !birthdate.value ||
    !nickName.value

  ) {
    return;
  }
  
  try {
    const response = await signUp(
      userId.value, 
      password.value, 
      passwordConfirm.value,
      email.value, 
      fullname.value,
      birthdate.value, 
      nickName.value
    );

    alert('회원가입 성공!', '날숨의 가족이 되신것을 환영해요!'); 
    console.log('회원가입 성공:', response.data)
    return response.data; 
  } catch (error) {
    console.error('회원가입 요청 에러:', error);
    alert('앗! 문제가 생겼어요. 재시도 바랍니다.')
    // throw error; // 필요에 따라 오류를 다시 던질 수 있습니다. 이 경우, 오류를 처리할 수 있는 상위 로직이 필요합니다.
  }
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


const queryParams = new URLSearchParams(window.location.search);
    const code = queryParams.get('code');
    
    if (code) {
      // 인가 코드를 로컬 스토리지에 저장
      localStorage.setItem('kakao_outh_code', code);

      // 추가적인 처리가 필요할 수 있음
    }

const sendKakaoTokenToServer = async () => {
  const kakaoToken = localStorage.getItem('kakao_outh_code');
  if (!kakaoToken) {
    console.error('카카오 인증 코드가 없습니다.');
    return;
  }

  try {
    const response = await axios.post('api/outh/kakao/login', {
      kakaoToken: kakaoToken
    });

    // 서버 응답 처리
    console.log('서버 응답:', response.data);
  } catch (error) {
    console.error('서버 요청 에러:', error);
  }
};

// sendKakaoTokenToServer();

// const isIdDuplicated = async (userId) => {
//   try {
//     const response = await axios ({
//     method: 'post',
//     url: 'api/general/id',
//     headers: {
//       "Content-Type":"application/json"
//     },
//     data: {
//       login_id: userId.value,
//     }
//     });

//     if (response.data === 2) {
//       console.log('이미 존재하는 아이디입니다.')
//       return true;  // true이면 input 초기화
//     } else {
//       console.log('사용 가능한 아이디입니다.')
//       return response.data, false; // false일 경우 그대로 
//     }
//   } catch (error) {
//     console.error('아이디 중복 확인 요청 에러:', error)
//     throw error;
//   }};


const isEmailDuplicated = async (email, emailDomain) => {
  try {
    const response = await axios ({
      method: 'post',
      url: 'api/email',
      headers: {
      "Content-Type":"application/json"
    },
    data: {
      email_id: email.value,
      email_domain : emailDomain.value
    }
    });
    if (response.data === 2) {
      console.log('이미 존재하는 이메일입니다.')
      return true
    }
    else if (response.data === 3) {
      console.log('유효하지 않은 이메일입니다.')
      return true
    }
    else {
      console.log('사용가능한 이메일입니다.')
      return response.data, false
    }
  } catch (error) {
    console.error('이메일 중복 확인 요청 에러:', error)
    throw error;
  }};
  

const verifyNumberCreate = async (email, emailDomain) => {
  try {
    const response = await axios ({
    method: 'post',
    url: 'api/email/request',
    headers: {
      "Content-Type":"application/json"
    },
    data: {
      email_id: email.value,
      email_domain : emailDomain.value
    }
  });
  if (response.data === 2) {
      console.log('이미 존재하는 이메일입니다.')
      return true
    }
    else if (response.data === 3) {
      console.log('유효하지 않은 이메일입니다.')
      return true
    }
    else {
      console.log('사용가능한 이메일입니다.')
      return response.data, false
    }
  } catch (error) {
    console.error('이메일 중복 확인 요청 에러:', error)
    throw error;
  }};
  

const emailVerifyRequest = async (email, emailDomain) => {
  try {
    const response = await axios({
      method: 'post',
      url: 'api/email/check',
      headers: {
        'Content-Type': 'application/json'
      },
      data: {
        email_id : email.value,
        email_domain: emailDomain.value
      }
    });
    
    if (response.data && response.data !== 1 || 2) {
      // 서버로부터 응답을 받으면 인증 코드 저장
      const verificationCode = response.data.verificationCode; 
      localStorage.setItem(code, verificationCode);
      console.log('사용가능한 이메일입니다.')
    }
    else {
      if (response === 2) {
        console.log('이미 가입된 이메일입니다.')
        return true
      }
      else {
        console.log('유효하지 않은 이메일입니다.')
        return true
      }
    }
  } 
    catch (error) {
    // 아예 에러 처리
    console.error('이메일 인증 요청 에러:', error);
    throw error;
  }
};

const Join = async (userId, email, fullname, birthdate, password, nickName) => {
try {
    const response = await axios ({
    method: 'post',
    url: 'http://i10b208.p.ssafy.io/general/join',
    headers: {
      "Content-Type":"application/json"
    },
    data: {
      login_id : userId,
      password : password,
      name : fullname,
      email_id : email,
      // email_domain : emailDomain,
      birth : birthdate,
      nickname : nickName,
    }
})
alert.log('회원가입 성공:');
    return response.data; 
  } catch (error) {
    console.error('회원가입 요청 에러:', error);
    throw error;
  }
};



// const snsLogin = (type) => {
//     window.location.href = `${store.API_URL}/api/user/login/${type}`;
// };

// const passwordInput = document.getElementById('password');

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
  .input-danger{
  border-bottom: 2px solid red !important;
}

.title-danger{
  color: red;
}

.input-error {
    line-height: 16px;
    font-size: 11px;
    color: red;
  }
</style>