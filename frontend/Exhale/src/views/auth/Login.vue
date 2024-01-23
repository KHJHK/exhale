<template>
    <div class="app">
        <h1>로그인</h1>
        <p>로그인 하고 완치를 향해 나아가보세요.</p>
        <p>누구나 극복할 수 있습니다!</p>
    </div>
    <div class="loginform">
        <div v-if="state.logedin">날;숨입니다. 여러분의 회복을 기원합니다.</div>
        <div v-else>
            
            <input type="text" id="loginId" placeholder="아이디">
            <br>  
            <input type="text" id="loginPw" placeholder="비밀번호">
            <br>
            <button style="width:170px">로그인</button>
        </div>
        </div>

</template>

<script>
import {reactive} from 'vue';
export default {
    setup() {
        const state = reactive({
            logedin: false
        });
        return {state};
    }
};


</script>

<style scoped>
.app {
    margin-left: 200px;
}

.loginform {
    margin-left : 700px;
    margin-top: -100px;
}
</style>