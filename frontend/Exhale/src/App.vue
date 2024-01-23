<template>
  <header>
  <div class="container">
    <nav>
      <RouterLink :to="{name: 'Home'}">
        <img src="@/assets/logo_green.png" alt="logo" class="logo" >
      </RouterLink>
          <RouterLink :to="{name: 'AboutUs'}">날숨은 무엇인가요?</RouterLink>
    
          <RouterLink :to="{name: 'Login'}">로그인</RouterLink>
    
          <RouterLink :to="{name: 'SignUp'}">회원가입</RouterLink>
      
      </nav>
  </div>  
  <div class="contents">
    <RouterView/>
  </div>
  </header>

</template>

<script>
// import {createApp, onMounted, ref} from 'vue';
import {reactive} from 'vue';
import axios from 'axios';
// import {useRouter} from 'vue-router';
// import { useCounterStore } from '@/stores/counter'; 

// import { createRouter, createWebHistory } from 'vue-router';
export default {
  setup() {
    const state = reactive({
      logedin: false
    });

    axios.get('http://local~/api/account').then((res)=>{
      console.log(res)
    })
    return (state);
  }
}



</script>

<style scoped>
nav {
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.container {
  margin-right: 20px;

}
.logo {
  width: 80px;
  height: auto;
  margin-right: 20px;
}
nav a {
  
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
  margin-top: 1px;
  justify-content: end;
  
  
}
nav a:first-of-type {
  border: 0;
}

.contents {
  border: 0;
}

.container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  
}

.logo {
  position : static ;
}
</style>
