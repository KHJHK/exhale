<template>
  <div class="parent-textbox">
    <div class="old-password-textbox">
      <input
        class="old-password-input"
        type="text"
        v-model="oldPassword"
        placeholder="현재 비밀번호 입력"
        :disabled="isInputDisabled"
      />
      <button class="password-btn, btn" @click="checkPassword">
        비밀번호 인증
      </button>
    </div>
    <!-- <input type="text" placeholder="이메일 입력" />
    <button class="email-btn, btn">인증번호 전송</button> -->
    <div class="child-textbox">
      <input type="text" v-model="newPassword" placeholder="새 비밀번호 입력" />
      <input type="text" placeholder="새 비밀번호 재입력" />
      <button class="save-btn, btn" @click="changePassword">저장</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import * as mypage from "@/api/mypage";

const oldPassword = ref("");
const newPassword = ref("");
const email = ref("");
const isInputDisabled = ref(false);

const checkPassword = async () => {
  //여기서 try-catch를 해버리니까 console에 mypage 로직 처리중 발생한 에러 로그가 남음
  try {
    const response = await mypage.checkPassword(oldPassword.value);
    isInputDisabled.value = !isInputDisabled.value;
  } catch (error) {
    const errorType = error.response.data.dataStatus;
    if (error.response.status) {
      if (errorType === null) {
        console.log();
        console.log("입력 타입 에러");
        return;
      }
      if (errorType.code === 6) {
        console.log("비밀번호 불일치");
        return;
      }
    }

    if (error.response.status) {
      console.log("권한이 없습니다.");
      return;
    } else {
      console.log("처리중 오류 발생 : " + error.response.status);
    }
    //인가(토큰만료 등) 에러 잡을 코드도 있는게 좋아보임
  }
};

const changePassword = async () => {
  const response = await mypage.rePassword(
    oldPassword.value,
    newPassword.value
  );
};
</script>

<style lang="scss" scoped>
.parent-textbox {
  display: flex;
  flex-direction: column;
  gap: 20px; /* 컴포넌트 간 간격 설정 */
}

.child-textbox {
  display: flex;
  flex-direction: column;
  width: 500px; //크기 임시조절
}

.old-password-textbox {
  display: flex;
  gap: 10px; /* 컴포넌트 간 간격 설정 */
}

.btn {
  padding: 8px 16px; /* 버튼 패딩 설정 */
  background-color: #4caf50; /* 버튼 배경색 설정 */
  color: white; /* 버튼 텍스트 색상 설정 */
  border: none; /* 버튼 테두리 없애기 */
  border-radius: 4px; /* 버튼 모서리를 둥글게 만들기 */
  cursor: pointer; /* 커서 스타일 변경 */
}

.save-btn {
  width: 100px;
}
</style>
