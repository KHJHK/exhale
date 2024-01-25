<template>
    <div>
        <h2>게시글 상세</h2>
        <hr class="my-4" />
        <p>params: {{ $route.params }}</p>
    </div>
</template>

<script setup>
import { useRoute } from 'vue-router'
const route = useRoute()
const articleId = route.params.articleId
</script>

<style lang="scss" scoped>

</style>