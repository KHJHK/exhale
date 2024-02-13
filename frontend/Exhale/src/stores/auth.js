// stores/auth.js
import { defineStore } from 'pinia';
import { useRouter } from 'vue-router'
import axios from 'axios'
import { ref, computed } from 'vue'


export const useAuthStore = defineStore('auth', () => {
  const router = useRouter()
  const loginUser = ref([])
  const comments = ref([])
  const replies = ref([])
  const JWTtoken = ref(localStorage.getItem('JWT_token'))
  const refreshToken = ref(localStorage.getItem('refresh_token'))
  const KEY = ref(localStorage.getItem('key'))
  const isAuthenticated = computed(() => !!JWTtoken.value)

 

  const isLogIn = computed(() => {
    if (localStorage.JWT_token !== undefined) {
    console.log('있나없나',localStorage.JWT_token)
    return true
    } else {
      console.log('있나없나',localStorage.JWT_token)
    return false
    }
})

  const isUser = () => {
    axios({
      method: 'get',
      url: `${API_URL}/auth/user`,
      headers: {
        Authorization: `Token ${token.value}`
      }
    })
    .then(res => {
      loginUser.value = res.data
    })
    .catch(err => {
      console.log(err)
    })
  }



  return {isLogIn, JWTtoken, refreshToken, KEY, isAuthenticated}
} , { persist: true })


