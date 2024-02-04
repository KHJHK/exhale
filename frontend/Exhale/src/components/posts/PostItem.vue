<template>
  <div class="postcard" @click="handleClick">
    <div class="card-number">
        <p>{{ number }}</p>
    </div>
    <div class="card-body">
        <p class="card-title">{{ title }}</p>
        <p class="card-content">{{ content }}</p>
        <p class="card-author">{{ author }}</p>
        <p class="text-muted"><small class="text-body-secondary">{{ create_date }}</small></p>
    </div>
    <div class="card-image">
        <img src="@/assets/dog3.jpg">
    </div>
</div>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue'

const props = defineProps({
  number: Number,
  title: String,
  content: String,
  author: String,
  create_date: [String, Date, Number],
  id: Number,
})


const emit = defineEmits(['go-to-detail'])
const handleClick = () => {
  emit('go-to-detail', props.id)
}
</script>

<style lang="scss" scoped>
  @import "@/assets/scss/layout/_forms.scss";
</style>