<template>
  <div class="postcard" @click="handleClick">
    <div class="card-number">
    </div>
    <div class="card-body">
        <p class="card-title">{{ title }}</p>
        <p class="card-content">{{ content }}</p>
        <p class="card-author">{{ author }}</p>
        <p class="text-muted"><small class="text-body-secondary">{{ create_date.substring(0, 10)}}</small></p>
    </div>
    <div class="card-image">
      <img v-if="props.thumbnail" :src="props.thumbnail" :alt="title">
     <!-- :style="{ backgroundImage: 'url(' + imageUrl + ')' , backgroundSize: 'cover', backgroundPosition: 'center' }" > -->
      <img v-if="props.thumbnail === null" src="@/assets/dog3.jpg">
    </div>
</div>
</template>

<script setup>
import { defineProps, defineEmits, ref , watchEffect } from 'vue'
const props = defineProps({
  thumbnail: String,
  title: String,
  content: String,
  author: String,
  create_date: [String, Date, Number],
  id: Number,
})
const article_id = ref([props.id])
const imageUrl = ref('')

const emit = defineEmits(['board_detail'])
const handleClick = () => {
  emit('board_detail', article_id)
}

watchEffect(() => {
  imageUrl.value = props.thumbnail
  
  
})
</script>

<style lang="scss" scoped>
  @import "@/assets/scss/layout/_forms.scss";
</style>