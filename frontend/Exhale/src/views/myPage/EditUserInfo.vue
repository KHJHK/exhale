<template>
  <header>
    <SimpleHdear />
    <div class="sub-nav1">
      <div id="breadcrum">
        <RouterLink class="breadlink" :to="{ name: 'MainPage' }"
          >메인 홈</RouterLink
        >
        >
        <RouterLink class="breadlink" :to="{ name: 'ARCReport' }"
          >내정보</RouterLink
        >
        >
        <!-- to를 자기 자신(ChangePassword)로  잡으면 에러남-->
        <RouterLink class="breadlink" :to="{ name: 'EditUserInfo' }"
          >회원정보수정</RouterLink
        >
      </div>
    </div>
  </header>

  <main>
    <h2>사용자 정보 변경</h2>
    <label class="preview" for="file">
      <img :src="image" />
    </label>
    <input
      class="file"
      id="file"
      type="file"
      accept="image/*"
      @change="changeImage"
    />
    <input type="text" v-model="nickname" placeholder="닉네임" />
    <button name="upload" class="upload" @click="editProfile">저장</button>
  </main>

  <footer class="footer">
    <Footers />
  </footer>
</template>

<script setup>
import * as editUserProfile from "@/api/editUserProfile";
import { ref, onMounted } from "vue";
import * as mypage from "@/api/mypage";

import SimpleHdear from "@/components/common/SimpleHeader.vue";
import Footers from "@/components/common/Footers.vue";

const image = ref(null);
const nickname = ref("");
let file = null;

const getProfile = async () => {
  const response = await mypage.getProfile();
  // nickname.value = response.data.response.nickname;
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

<style lang="scss" scoped>
main {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 70vh;
}

footer {
  position: fixed;
  bottom: 0;
  width: 100%;
  margin-bottom: 15px;
}
.upload {
  font-size: 20px;
  font-weight: bold;
  padding: 8px 16px; /* 버튼 패딩 설정 */
  background-color: #6c9f9c; /* 버튼 배경색 설정 */
  color: white; /* 버튼 텍스트 색상 설정 */
  border: none; /* 버튼 테두리 없애기 */
  border-radius: 10px; /* 버튼 모서리를 둥글게 만들기 */
  cursor: pointer; /* 커서 스타일 변경 */
}

.file {
  display: none;
}

.preview {
  width: 200px;
  height: 200px;
}

.preview:hover {
  cursor: pointer;
}

.preview > img {
  width: 100%;
  height: 100%;
  border-radius: 70%;
}
</style>
