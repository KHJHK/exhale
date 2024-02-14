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

// export default {
//   mounted() {
//     // URL에서 인가 코드 추출
//     const queryParams = new URLSearchParams(window.location.search);
//     const code = queryParams.get('code');
    
//     if (code) {
//       // 인가 코드를 로컬 스토리지에 저장
//       localStorage.setItem('kakao_auth_code', code);

//       // 추가적인 처리가 필요할 수 있음
//     }
//   }
// }
const value = localStorage.getItem('kakao_auth_code')

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