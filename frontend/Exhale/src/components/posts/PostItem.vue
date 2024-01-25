<template>
  <div class="card mb-3" style="max-width: 540px;" @click="handleClick">
    <div class="row">
        <div class="col-md-2 d-flex align-items-center justify-content-center">
            <h2 class="card-number">{{ number }}</h2>
        </div>
        <div class="col-md-6">
            <div class="card-body">
                <h5 class="card-title">{{ title }}</h5>
                <p class="card-text">{{ content }}</p>
                <p class="text-muted"><small class="text-body-secondary">{{ create_date }}</small></p>
            </div>
        </div>
        <div class="col-md-4">
            <img src="@/assets/dog3.jpg" class="img-fluid rounded-start" alt="...">
        </div>
    </div>
  </div>

</template>

<script setup>
import { defineProps, defineEmits } from 'vue'

const props = defineProps({
  number: Number,
  title: String,
  content: String,
  create_date: [String, Date, Number],
  articleId: Number,
})


const emit = defineEmits(['go-to-detail'])
const handleClick = () => {
  emit('go-to-detail', props.articleId)
}
</script>

<style lang="scss" scoped>

</style>