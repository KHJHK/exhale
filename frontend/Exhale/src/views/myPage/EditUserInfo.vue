<template>
  <p @search="getUserPorfile">{{ nickname }}</p>
  <input type="text" v-model="nickname" :placeholder="nickname" />
  <input type="file" accept="image/*" @change="changeImage" />
  <div class="preview">
    <img :src="image" />
  </div>
  <button name="upload" @click="editProfile">upload</button>
</template>

<script setup>
import * as editUserProfile from "@/api/editUserProfile";
import { ref, onMounted } from "vue";
import * as mypage from "@/api/mypage";

const image = ref(null);
const nickname = ref("");
let file = null;

const getProfile = async () => {
  const response = await mypage.getProfile();
  nickname.value = response.data.response.nickname;
  image.value = response.data.response.image_url;
};

const changeImage = (event) => {
  const files = event.target?.files;
  if (files.length > 0) {
    file = files[0];

    // FileReader 객체 : 웹 애플리케이션이 데이터를 읽고, 저장하게 해줌
    const reader = new FileReader();

    // load 이벤트 핸들러. 리소스 로딩이 완료되면 실행됨.
    reader.onload = (e) => {
      image.value = e.target.result;
    }; // ref image 값 변경

    // 컨텐츠를 특정 file에서 읽어옴. 읽는 행위가 종료되면 loadend 이벤트 트리거함
    // & base64 인코딩된 스트링 데이터가 result 속성에 담김
    reader.readAsDataURL(file);
  }
};

const editProfile = async () => {
  try {
    const formData = new FormData();
    formData.append("image", file);

    //이미지 크기가 너무 크면 저장 안됨. 줄여주기 필요
    const response = await editUserProfile.editUserProfile(
      nickname.value,
      formData
    );

    console.log(response);
  } catch (error) {
    console.log("error : ", error);
  }
};

onMounted(() => {
  getProfile();
});
</script>

<style lang="scss" scoped></style>
@/api/editUserProfile
