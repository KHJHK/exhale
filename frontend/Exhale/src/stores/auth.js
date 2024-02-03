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
  const token = ref(localStorage.getItem('token') || null)
  const isAuthenticated = computed(() => !!token.value)

 





  const isLogIn = computed(() => {
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
  return 
})


