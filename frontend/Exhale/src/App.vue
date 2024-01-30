<template>
  <div class="head">
    <main>
      <RouterView/>
    </main>
  </div>
</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router';
import {createApp, onMounted, ref} from 'vue';
import {reactive} from 'vue';
import axios from 'axios';
</script>

<style>

.head {
  background-image: url("@/assets/background_origin1.png");
  background-size: contain;
  height: auto;
  min-height: 100%;
}
</style>
