<template>
  <header>
    <Headers />
  </header>
  <main>
    <RouterView/>
  </main>
  <footer>
    <Footers/>
  </footer>

</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router';
import {createApp, onMounted, ref} from 'vue';
import {reactive} from 'vue';
import axios from 'axios';
import MainPage from './views/main/MainPage.vue';
import Headers from './components/common/Headers.vue';
import Footers from './components/common/Footers.vue';
</script>

<style>

</style>
