<template>
  <input type="file" accept="image/*" @change="changeImage" />
  <div class="preview">
    <img :src="image" />
  </div>
  <button name="upload" @click="editProfile">upload</button>
</template>

<script setup>
import * as editUserInfo from "@/api/editUserInfo";
import { ref, onMounted } from "vue";

const image = ref("");
const nickname = ref("aaa");
const token =
  "Bearer eyJhbGciOiJIUzI1NiJ9.eyJsb2dpbl9pZCI6InNzYWZ5MSIsIm1lbWJlcl9pZCI6Nywicm9sZSI6IlJPTEVfVVNFUiIsImlhdCI6MTcwNzY1Mzk4NywiZXhwIjoxNzA3NjU1Nzg3fQ.Dj2OdMASmFL8QBZmhiwpwvKdo-Yx-Ylvy3APry4DaaI";

const changeImage = (event) => {
  const files = event.target?.files;
  if (files.length > 0) {
    const file = files[0];

    // FileReader 객체 : 웹 애플리케이션이 데이터를 읽고, 저장하게 해줌
    const reader = new FileReader();

    // load 이벤트 핸들러. 리소스 로딩이 완료되면 실행됨.
    reader.onload = (e) => {
      image.value = e.target.result;
    }; // ref previewImage 값 변경

    // 컨텐츠를 특정 file에서 읽어옴. 읽는 행위가 종료되면 loadend 이벤트 트리거함
    // & base64 인코딩된 스트링 데이터가 result 속성에 담김
    reader.readAsDataURL(file);
  }
};

const editProfile = async () => {
  try {
    const response = editUserInfo.editNickname(nickname.value, token);
    // const response = editUserInfo.editProfileImage(image.value, token);
    // const response = editUserInfo.editUserInfo(
    //   nickname.value,
    //   image.value,
    //   token
    // );
    console.log("response : " + response.value);
  } catch (error) {
    console.log("error : ", error);
  }
};
</script>

<style lang="scss" scoped>
svg {
  width: 100px;
  height: 100px;
}
</style>
