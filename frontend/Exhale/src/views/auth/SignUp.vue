<template>
  <!-- <img src="@/assets/logo_white.png" class="intro-logo"> -->
  
  <div :style="{ fontSize: fontSize + 'px' }">
    <div  class="container" ref="container">
      <button class="enlarge" @click="enlarge" style="z-index: 10;">
        <img src="@/assets/plus.svg" class="plus">
        {{ msg }}
      </button> 
      <!-- FORM SECTION -->
      <form>
        <!-- SIGN UP -->
        <div class="col align-items-center flex-col sign-up">
          <img src="@/assets/logo_white.png" class="intro-logo">
          <div class="form-wrapper align-items-center">
            <div class="form sign-up">
              <div class="input-group">
                <i class='bx bxs-user'></i>
                <p v-show="userIdErr" style="color: red; font-weight: bold;" >
                  아이디는 4자리 이상의 영문,숫자가 조합되어야합니다.
                </p>
                <p v-show="userIdDuplicated" style="color: red; font-weight: bold;" >
                  다른 유저와 중복되는 아이디 입니다.
                </p>
                <div class="input-group-flex">
                  <input class="input" v-model.trim="userId" id="userId" placeholder="아이디" type="text" />
                  <button class="doubleCheck" @click.prevent="id_Duplicated">중복확인</button>
                </div>
              </div>
              <div class="input-group">
                <i class='bx bx-mail-send'></i>
                <p v-show="emailErr" style="color: red; font-weight: bold;">
                  유효한 이메일 주소를 입력하세요.
                </p>
                <div class="input-group-flex">
                  <input class="input" v-model.trim="email" id="email" placeholder="이메일" type="email" /> 
                  <button class="doubleCheck" @click.prevent="handleClick">중복확인</button>
                  <EmailAuthentication v-if="show" />
                </div >
                
                
              </div>
              <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <input class="input" v-model.trim="fullname" id="fullname" placeholder="성명" type="text"/>
              </div>
              
              <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <input class="input" v-model.trim="birthdate" id="birthdate" placeholder="생년월일" type="Date"/>
              </div>
              
              <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <p v-show="passwordErr" style="color: red; font-weight: bold;">
                  비밀번호는 10자리 이상의 영문,숫자,특수문자가 조합되어야 합니다.
                </p>
                <input class="input" v-model="password" id="password" :type="passwordType" placeholder="비밀번호"/>
                <span @click.prevent="toggleVisibility1('password')" class="eye-icon">
                  <img src="@/assets/eye.png" alt="">
                </span>
              </div>
              <div class="input-group">
                <p v-show="passwordConfirmErr" style="color: red; font-weight: bold;">
                  비밀번호와 비밀번호확인이 다릅니다.
                </p>
                <i class='bx bxs-lock-alt'></i>
                <input v-model="passwordConfirm" id="password-confirm" :type="passwordConfirmType" placeholder="비밀번호 확인"/>
                <span @click.prevent="toggleVisibility2('passwordConfirm')" class="eye-icon">
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
                <b @click="toggle" class="pointer">로그인</b>
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
            <!-- <h2>Sign Up</h2> -->
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
import { useRoute, useRouter } from 'vue-router';
import { RouterLink } from 'vue-router';
import { useAuthStore } from '@/stores/auth';
import { isIdDuplicated ,signUp, kakaoLogin, verifyNumberCreate, logOut } from '@/api/outhApi.js';
import EmailAuthentication from '@/components/modals/EmailAuthentication.vue'

const store = useAuthStore()
const fontSize = ref(16);
const router = useRouter();
const userId = ref('');
const email = ref('');
const email_id = ref('');
const email_domain = ref('');
const fullname = ref('');
const birthdate = ref('');
const password = ref('');
const passwordConfirm = ref('');
const nickName = ref('');
const passwordType = ref("password");
const passwordConfirmType = ref("password");


// 이메일 인증 모달 창
const show = ref(false)

const handleClick = () => {
  show.value = !show.value;
}

// 이메일 도메인 분리
watch(email, () => {
    const emailArr = email.value.split("@");
    if (emailArr.length === 2) {
        email_id.value = emailArr[0]; // 사용자 이름 부분 할당
        email_domain.value = emailArr[1]; // 도메인 부분 할당
    }
});

// 에러변수 Boolean
const userIdErr = ref(false);
const emailErr = ref(false);
const passwordErr = ref(false);
const passwordConfirmErr = ref(false);
const userIdDuplicated = ref(false)
//유저아이디 조건
function check_userId() {
  const userIdValid = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{4,}$/
  userIdErr.value = !userIdValid.test(userId.value)
}
watch(userId, ()=> {
  check_userId();
})

//유저이메일 조건
function check_email() {
  const emailValid = /^[^\s@]+@[^\s@]+\.[^\s@]{3,}$/;
  emailErr.value = !emailValid.test(email.value);
}
watch(email, () => {
  check_email();
})

//유저비밀번호 조건
function check_password() {
  const passwordValid = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{10,}$/;
  passwordErr.value = !passwordValid.test(password.value);
}
watch(password, () => {
    check_password()
})
const idDuplicated = ref(false)
//유저 비밀번호확인
function check_passwordConfirm() {
  if (password.value !== passwordConfirm.value){
    passwordConfirmErr.value = true
  }
  else {
    passwordConfirmErr.value = false
  }
}
watch(passwordConfirm, () => {
  check_passwordConfirm()
});

// Id 중복확인
const id_Duplicated = async () => {
  if (!userId.value || userIdErr.value) {  
    return;
  }
 const response =  await isIdDuplicated(
     userId.value
)
userIdDuplicated.value = response
};


// 회원가입 
const sign_up = async () => {
  if (!userId.value || !email.value || !password.value || !passwordConfirm.value || userIdErr.value || userIdDuplicated.value || emailErr.value || passwordErr.value || passwordConfirmErr.value) {
    alert('입력 값들을 다시 확인해주세요.');
    return;
  }
    try {
      const response = await signUp(
        userId.value, 
        email_id.value,
        email_domain.value, 
        fullname.value,
        birthdate.value, 
        password.value, 
        nickName.value
        );
        
        router.push('/login')
      } catch (error) {
        }};

  // 글자크기조절 버튼
const msg = computed(() => fontSize.value > 28 ? '원래대로' : '글자확대');
const enlarge = () => {
  fontSize.value += 4;
  if (fontSize.value > 32) {
    fontSize.value = 16
  }
}

// 암호화 on/off
const toggleVisibility1 = (field1) => {
  if (field1 === 'password') {
    passwordType.value = passwordType.value === 'password' ? 'text' : 'password';
  }}; 
const toggleVisibility2 = (field2) => {
  if (field2 === 'passwordConfirm') {
    passwordConfirmType.value = passwordConfirmType.value === 'password' ? 'text' : 'password';
  }
  };


const queryParams = new URLSearchParams(window.location.search);
    const code = queryParams.get('code');
    
    if (code) {
      // 인가 코드를 로컬 스토리지에 저장
      localStorage.setItem('kakao_outh_code', code);

      // 추가적인 처리가 필요
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



const verify_Number_Create = async () => {

    const response = await verifyNumberCreate(
      email_id.value,
      email_domain.value
      )
      console.log(response)
  };
  

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

////////////////////////////////////////////////////////
const toggle = () => {
    router.push({ name: 'Login'})
  };
const container = ref(null);

// container ref가 mount 되었을 때 sign-in class 추가
  onMounted(() => {
  setTimeout(() => {
    if (container.value) {
      container.value.classList.add('sign-up')
    }
  }, 300)
})
////////////////////////////////////////////////////////

</script>

<style lang="scss" scoped>
  @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600&display=swap');
  @import "@/assets/scss/pages/_signup.scss";
  /* .input-danger{
  border-bottom: 2px solid red !important;
  color: red;
}

.title-danger{
  color: red !important;
}

.input-error {
    line-height: 16px;
    font-size: 11px;
    color: red;
  } */


</style>
