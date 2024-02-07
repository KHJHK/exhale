<template>

<div class="authentication">
  
  <img src="@/assets/Nav.png" alt="hamburgerMenu" class="hamburgerMenu" >
  <hr class="">
  <button>닫기</button>
  
  <p>날;숨 서비스 이용을 위해</p>
  <p>이메일 인증이 한 번 필요해요.</p>
  
  <label for="">이메일로 전송된 인증코드를 입력해주세요.</label>
  <div class="text-center">
    <v-otp-input
      focus-all
      v-model="otp"
      :loading="loading"
      length="6"
      variant="underlined"
      class="numberInput"
    ></v-otp-input>

    <v-btn
      :disabled="otp.length < 6 || loading"
      class="my-5"
      color="surface-variant"
      text="Submit"
      variant="tonal"
      @click="onClick"
    ></v-btn>
  </div>
  <p>! 이메일을 받지 못하샸나요? 
    <a href="">이메일 재전송하기</a>
  </p>

  <button>

  </button>

</div>
</template>

<script setup>
import { ref } from 'vue';

// 반응형 데이터 정의
const loading = ref(false);
const otp = ref('');

// 메소드 정의
const onClick = () => {
  loading.value = true;

  setTimeout(() => {
    loading.value = false;
  }, 2000);
};

</script>

<style lang="scss" scoped>
.authentication {
  z-index: 10;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgb(108, 159, 156);
  opacity: 80%;

}

.numberInput {
  position: fixed;
  top: 40vh;
  left: 38vw;
  color: white;
  font-family: 'NotoSansKR';
  border: 2px solid white;
  padding: 20px 40px;

}
</style>