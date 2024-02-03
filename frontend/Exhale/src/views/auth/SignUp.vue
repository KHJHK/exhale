  <template>
        <button class="enlarge" @click="enlarge">{{ msg }}</button>    
      <div class="p" :style="{ fontSize: fontSize + 'px' }">
        <h1>회원가입</h1>
        <p>희망을 잃지 마세요!
          환영합니다! 4종 1,000개가 넘는 언어 재활 코스와 함께 일상으로의 복귀를 도와드리고 있어요. 함께 노력해봐요.
        </p>
        </div>   
            <div class="form-wrap">
              <form @submit.prevent="submitForm">
                <div class="input-with-label" >
                  <input v-model.trim="userId" id="userId" placeholder="아이디" type="text" style= "border: 2px solid rgb(108, 159, 156); border-radius: 30px; text-align: center;"/>
                  <button @click="isIdDuplicated">중복확인</button>
                </div>
                <div class="input-with-label">
                  <input  v-model.trim="email" id="email" placeholder="이메일" type="text" style="border: 2px solid rgb(108, 159, 156); border-radius: 30px; text-align: center;"/>
                </div>
                <div class="input-with-label">
                  <input   v-model.trim="fullname" id="fullname" placeholder="성명" type="text" style="border: 2px solid rgb(108, 159, 156); border-radius: 30px; text-align: center;" />
                </div>
                <div class="input-with-label">
                  <input v-model.trim="birthdate" id="birthdate" placeholder="생년월일" type="date" style="border: 2px solid rgb(108, 159, 156); border-radius: 30px; text-align: center;"/>
                </div>
                <div class="input-with-label">
                  <input v-model="password" id="password" :type="passwordType" placeholder="비밀번호" style="border: 2px solid rgb(108, 159, 156); border-radius: 30px; text-align: center;" />
                  <button @click="toggleVisibility1('password')" class="eye">
                    <img src="@/assets/eye.png" alt="eye-icon" >
                  </button>
                </div>
                <div class="input-with-label">
                  <input v-model="passwordConfirm" id="password-confirm" :type="passwordConfirmType" placeholder="비밀번호 확인"  style="border: 2px solid rgb(108, 159, 156); border-radius: 30px; text-align: center;" />
                  <button @click="toggleVisibility2('passwordConfirm')" class="eye">
                    <img src="@/assets/eye.png" alt="eye-icon" >
                  </button>
                </div>
                <div class="input-with-label">
                  <input v-model="nickName" id="nickname" placeholder="닉네임" type="text"  style="border: 2px solid rgb(108, 159, 156); border-radius: 30px; text-align: center;" />
                </div>
                <div class="submit1" >
                    <button class="btn-bottom" style="border: 2px solid rgb(108, 159, 156); border-radius: 30px; width: 170px;" type="submit" @click="sign_up" >회원가입</button> 
                  <br>
                  <div class="kakaoLogin">
                    <!-- snsLogin('?kakao') -->
                    <button @click="kakaoLogin" style="width: 170px; border: 0px; background-color: white;">
                    <img src="@/assets/kakao_login.png" alt="login" style="width: 150px;">
                    </button>
                  </div>
                </div>
                <div style="display: inline">
                  이미 회원이세요? <RouterLink :to="{name: 'Login'}">로그인</RouterLink>
                </div>
              </form>
            </div>
            <div class="input-error">
              <p
              v-show="valid.userId">
              아이디를 정확히 입력해주세요
            </p>
            <p
            v-show="valid.password">
            비밀번호와 비밀번호확인이 다릅니다
          </p>
        </div>
          <footer>

            </footer>
            </template>
<script setup>
import { ref, computed, reactive, watch } from 'vue';
import { onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/auth';
import { isIdDuplicated, signUp } from '@/api/outhApi.js';



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
  };
};

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
  const clientId = "64f53b3a322ebb16eabd9859392720c9"; // 클라이언트 ID를 문자열로 설정
  const redirectUri = 'http://localhost:5173/';
  const url = `https://kauth.kakao.com/oauth/authorize?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code`
  // 사용자를 카카오 로그인 페이지로 리디렉션
  window.location.href = `https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${clientId}&redirect_uri=${redirectUri}`;
  
  return kakaoLogin
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

</script>
<style scoped>
img {
  width: 20px;
  height: auto;
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
.form-wrap {
    margin-left: 700px;
    margin-top: 100px;
    
}
.p {
  margin-left: 150px;
  margin-top: 100px;
}
.enlarge {
  margin-left: 1100px;
  width: 100px;
  border-radius: 40px;
  border: rgb(108, 159, 156) solid 2px;
  background-color: white;
  
}
.input-with-label {
  position: relative;
  display: flex;
  margin: 10px;

}

input {
  width: 170px;
  
}

.submit1 {
  display: flex;
  flex-direction: column;
  justify-content: center;

}
.kakaoLogin {
  margin-top: -10px;
  margin-left: 10px;

}

.btn-bottom{
  margin-left: 12px;

}

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