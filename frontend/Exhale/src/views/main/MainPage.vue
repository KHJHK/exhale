<template>
    <body>
        <p>메인페이지 전용 내용</p>
    </body>
</template>

<script setup>
import { ref, onMounted, onUnmounted, watch } from "vue";
import { useRouter } from "vue-router";
import Headers from "@/components/common/Headers.vue";
import { RouterView } from "vue-router";
</script>

<style scoped>
body {
    /* display: flex;
    justify-content: center;
    align-items: center; */
    text-align: center;
    margin-top: 200px;
}
</style>