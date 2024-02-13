<template>
  <div id="page">
    <MyPageHeader />
    <section class="sub-nav1">
      <div id="breadcrum" style="color: white">
        <RouterLink
          class="breadlink"
          style="color: white"
          :to="{ name: 'MainPage' }"
          >메인 홈</RouterLink
        >
        >
        <RouterLink
          class="breadlink"
          style="color: white"
          :to="{ name: 'PostWholeListView' }"
          >마이페이지</RouterLink
        >
      </div>
      <button
        class="enlarge"
        @click="enlarge"
        style="position: absolute; right: 0px; z-index: 10; border-color: white"
      >
        <img src="@/assets/plus.svg" class="plus" />
        {{ msg }}
      </button>
    </section>

    <div id="sidebar">
      <div id="profile">
        <div id="profile-image">
          <img id="profile-img-tag" v-bind:src="image" alt="프로필 사진" />
        </div>
        <p id="nickname-p-tag">{{ nickname }} 님의 <br />재활리포트</p>
      </div>
    </div>
    <div id="main">
      <div id="solved-data"></div>
      <div id="letter-data">bb</div>
    </div>
    <Footers />
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import MyPageHeader from "@/components/common/MyPageHeader.vue";
import Headers from "@/components/common/Headers.vue";
import Footers from "@/components/common/Footers.vue";

import * as report from "@/api/report";
import * as mypage from "@/api/mypage";

const nickname = ref("");
const image = ref("");
const letterDataList = [];
const solvedDataList = [];

const getProfile = async () => {
  const response = await mypage.getProfile();
  nickname.value = response.data.response.nickname;
  image.value = response.data.response.image_url;
  console.log(nickname.value);
  console.log(image.value);
};

const getLetterData = async () => {
  const response = await report.getLetterData();
  console.log(response);
};

const getSolvedData = async () => {
  const response = await report.getSolvedData();
  console.log(response);
};

onMounted(() => {
  getProfile();
  getLetterData();
  getSolvedData();
});
</script>

<style lang="scss" scoped>
@import "@/assets/scss/pages/_mainpage.scss";

#page {
  width: 100vw;
  height: 100vh;
  background-color: #6c9f9c;
}

//sidebar 관련
#sidebar {
  min-height: 80%;
  width: 25%;
  padding: 0% 7%;
  //background-color: rgb(169, 170, 170);
  position: absolute;
  left: 0;
}

#profile-image {
  display: flex;
  justify-content: center;
  margin-top: 60px;
}

#profile-img-tag {
  width: 170px;
  height: 170px;
  border-radius: 50%;
  object-fit: cover;
}

#nickname-p-tag {
  margin: 10px 0px;
  font-size: 20px;
  text-align: center;
  color: white;
}

//main 관련
#main {
  min-height: 80%;
  width: 75%;
  //background-color: aquamarine;
  margin-left: 25%;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

#solved-data {
  width: 100px;
  height: 100px;
  background-color: brown;
}

#letter-data {
  width: 100px;
  height: 100px;
  background-color: bisque;
}

#mypage-header {
  color: white;
}
</style>
