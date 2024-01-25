<template>
  <header>
    <Headers/>
  </header>
    <RouterView/>
  <main>
  </main>
 
  

</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router';
import {createApp, onMounted, ref} from 'vue';
import {reactive} from 'vue';
import axios from 'axios';
import MainPage from './views/main/MainPage.vue';
import Headers from './components/common/Headers.vue';

// import {useRouter} from 'vue-router';
// import { useCounterStore } from '@/stores/counter'; 

// import { createRouter, createWebHistory } from 'vue-router';
const state = reactive({
  logedin: false
})
</script>

<style scoped>
/* nav {
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
} */


</style>
