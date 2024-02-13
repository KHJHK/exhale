<template>
  <div id="page">
    <SimpleHdear />
    <div id="sidebar">
      <div id="profile-image">
        <img id="profile-img-tag" v-bind:src="image" alt="프로필 사진" />
      </div>
      <div id="nickname"></div>
    </div>
    <div id="main">
      <div id="solved-data"></div>
      <div id="letter-data">bb</div>
    </div>
    백에서 곧 통계자료가 들어오면 간드러지게 게시할게요
    <Footers />
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import SimpleHdear from "@/components/common/SimpleHeader.vue";
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
#page {
  width: 100vw;
  height: 100vw;
}

#sidebar {
  min-height: 80%;
  width: 25%;
  padding: 0% 7%;
  background-color: rgb(169, 170, 170);
  position: absolute;
  left: 0;
}

#profile-image {
  margin: 30px 0px;
  width: 100%;
  height: 200px;
  border-radius: 50%;
  background-color: red;
}

#profile-img-tag {
  width: 100%;
  height: 100%;
}

#main {
  min-height: 80%;
  width: 75%;
  background-color: aquamarine;
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
</style>
