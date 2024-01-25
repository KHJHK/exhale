// stores/auth.js
import { defineStore } from 'pinia';
import { useRouter } from 'vue-router'
import axios from 'axios'
import { ref, computed } from 'vue'

export const useAuthStore = defineStore('auth', () => {
  const router = useRouter()
  const loginUser = ref([])
  const token = ref(null)
  
  const signup = (payload) => {
    const userId = payload.userId
    const email = payload.email
    const fullname = payload.fullname
    const birthdate = payload.birthdate
    const password = payload.password
    const passwordConfirmation = payload.passwordConfirmation
    const nickname = payload.nickname

    axios({
      method: 'post',
      url: `${API_URL}/auth/signup`,
      data: {
        userId,
        email,
        fullname,
        birthdate,
        password,
        passwordConfirmation,
        nickname, 
      }
    })
      .then(res => {
        const password = passwordConfirmation
        logIn({username,password})
      })
      .catch(err => console.log(err))
  }
  const logIn = (payload) => {
    const {userId, password} = payload

    axios({
      method: 'post',
      url: `${API_URL}/auth/login`,
      data: {
        userId, password
      }
    })
      .then(res => {
        token.value = res.data.key
        router.push({name: 'mainpage'})
        isUser()
      })
      .catch(err => console.log(err))
  }

  const logOut = () => {

    axios({
      method: 'post',
      url: `${API_URL}/auth/logout`,
      headers: {
        Authorization: `Token${token.value}`
      }
    })
    .then(res => {
      token.value = null
      loginUser.value = null
      console.log(res)
    })
    .catch(err => {
      console.log(err)
    })
  }

  const isLogIn = computed(() => {
    true
    if (token.value === null) {
      return false
    } else {
      return true
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
  return {signup,}
})

