<template>
    <div>
        <button class="withdraw-btn" @click="withdrawCheck">탈퇴 ㅋ</button>
    </div>
</template>

<script setup>
    import { ref, onMounted } from "vue";
    import { withDraw, logOut } from "@/api/outhApi"
    import { withdraw, logout } from "@/api/mypage"
    import { useRouter } from "vue-router"

    import SimpleHdear from "@/components/common/SimpleHeader.vue";
    import Footers from "@/components/common/Footers.vue";

    const router = useRouter();
    const token = localStorage.getItem("JWT_token");

    const withdrawCheck = () => {
        const result = confirm("회원 탈퇴를 진행하시겠습니까?")
        if(result){
            withdrawUser()
        }else{
            alert("회원탈퇴가 취소되었습니다.")
        }
    }

    const withdrawUser = async () => {
        const response = await withdraw();
        await logout();
        localStorage.removeItem("JWT_token")
        localStorage.removeItem("refresh_token")
        localStorage.removeItem("key")
        alert("회원탈퇴가 완료되었습니다.");
        router.push({ name: 'Login' })
    }
</script>

<style lang="scss" scoped>
    .widthdraw-btn{
        background-color: aquamarine;
    }
</style>