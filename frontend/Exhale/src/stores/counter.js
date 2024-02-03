import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'
import axios from 'axios'

export const useCounterStore = defineStore('counter', () => {
  const router = useRouter()
  const posts = ref([])
  const username = ref(localStorage.getItem('username')|| '')
  const API_URL = ''
  const token = ref(localStorage.getItem('token')|| null)
  const isAuthenticated = computed(() => !!token.value)
  
  // const signUp = function (payload) {
  //   const {userId, email, fullname, birthdate,password, passwordconfirm, nickname } = payload
  //   console.log('sending request with payload:',payload)
    
  //   axios({
  //     method: 'post',
  //     url: `${API_URL}/api/v1/accounts/signup/`,
  //     headers: {
  //       'Content-Type':'application/json'
  //     },
  //     data: JSON.stringify({
  //       userId, email, fullname, birthdate,password, passwordconfirm, nickname
  //     })
  //   })
  //   .then((res) => {
  //     console.log(res)
  //     const password = password
  //     logIn({ userId, email, fullname, birthdate,password, nickname})
  //   })
  //   .catch((err) =>{
  //     console.error('Error:', err)
  //   })
  // }

  // const logIn = function (payload) {
  //   const {userId, password} = payload

  //   axios({
  //     method: 'post',
  //     url: `${API_URL}/api/v1/accounts/login/`,
  //     data: { 
  //       userId, password
  //     }
  //   })
  //   .then((res) => {
  //     token.value = res.data.key
  //     userId.value = userId
  //     localStorage.setItem('token', token.value)
  //     localStorage.setItem('userId', userId)
  //     router.push({ name: 'Recommendation' })
  //   })
  //   .catch((err) => {
  //     console.log(err)
  //   })
  // }

  // const logOut = function () {
  //   token.value = null
  //   localStorage.removeItem('token')
  //   router.push({name: 'Intro'})
  // }

  return { posts, API_URL, getposts }
})
