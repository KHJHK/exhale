  // stores/auth.js
  import { defineStore } from 'pinia';
  import { useRouter } from 'vue-router'
  import axios from 'axios'
  import { ref, computed } from 'vue'


  export const useAuthStore = defineStore('auth', () => {
    const router = useRouter()
    // const loginUser = ref([])
    // const comments = ref([])
    // const replies = ref([])
    const jwtToken = ref('');
    const refreshToken = ref('');
    const key = ref('');
    const loginId = ref('');
    const memberId = ref(0);
    const nickname = ref('');
    const isLogin = ref(false);
    // const JWTtoken = ref(localStorage.getItem('JWT_token'))
    // const refreshToken = ref(localStorage.getItem('refresh_token'))
    // const KEY = ref(localStorage.getItem('key'))
    // const login = ref(false);
  

    // const isLogIn = computed(() => {
    //   console.log("ㅇㅇㅇㅇㅇ")
    //   if (localStorage.JWT_token !== undefined) {
    //   console.log('있나없나',localStorage.JWT_token)
    //     // login.value = true;
    //   return true
    //   } else {
    //     console.log('있나없나',localStorage.JWT_token)
    //     // login.value = false;
    //   return false
    //   }
    // })
    const saveUserInfo = (jwtTokenValue, refreshTokenValue, keyValue, loginIdValue, memberIdValue, nicknameValue) => {
      jwtToken.value = jwtTokenValue;
      refreshToken.value = refreshTokenValue;
      key.value = keyValue;
      loginId.value = loginIdValue;
      memberId.value = memberIdValue;
      nickname.value = nicknameValue;
      isLogin.value = true;
    }
    const removeUserInfo = () => {
      jwtToken.value = '';
      refreshToken.value = '';
      key.value = '';
      loginId.value = '';
      memberId.value = 0;
      nickname.value = '';
      isLogin.value = false;
    }
  
    
    // const isUser = () => {
    //   axios({
    //     method: 'get',
    //     url: `${API_URL}/auth/user`,
    //     headers: {
    //       Authorization: `Token ${token.value}`
    //     }
    //   })
    //   .then(res => {
    //     loginUser.value = res.data
    //   })
    //   .catch(err => {
    //     console.log(err)
    //   })
    // }


    return { saveUserInfo,removeUserInfo, isLogin, jwtToken, refreshToken, key, loginId, memberId, nickname}
  } , { persist: true })


  // persist: {
  //   enabled: true,
  //   strategies: [{ storage: localStorage }],
  // },