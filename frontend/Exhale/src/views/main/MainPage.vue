<template>
    <header>
        <Headers />
    </header>
    <body>
        <p>메인페이지 전용 내용</p>
    </body>
    <footer>
        <Footers/>
    </footer>

</template>

<script setup>
import { ref, onMounted, onUnmounted, watch } from "vue";
import { useRouter } from "vue-router";
import Headers from "@/components/common/Headers.vue";
import Footers from '@/components/common/Footers.vue';
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