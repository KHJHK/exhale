<template>
  <div id="page">
    <SimpleHdear />
    <div id="sidebar">
      <div id="profile-image"></div>
      <div id="nickname"></div>
    </div>
    <div id="main"></div>
    백에서 곧 통계자료가 들어오면 간드러지게 게시할게요
    <Footers />
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import SimpleHdear from "@/components/common/SimpleHeader.vue";
import Footers from "@/components/common/Footers.vue";

import * as mypage from "@/api/mypage";

const nickname = ref("");
const image = ref(null);

const getProfile = async () => {
  const response = await mypage.getProfile();
  nickname.value = response.data.response.nickname;
  image.value = response.data.response.image_url;
};

onMounted(() => {
  getProfile();
});
</script>

<style lang="scss" scoped>
.parent {
  width: 100%;
  height: 100vh;
  background-color: #6c9f9c;
}

main {
  display: flex;
  align-items: center;
  height: 60vh;
}

.info {
  width: 25%;
  align-items: center;
}

.profile-image {
  width: 200px;
  height: 200px;
}

.profile-image > img {
  width: 100%;
  height: 100%;
  border-radius: 70%;
}

.report {
  width: 75%;
  align-items: center;
}
.report > h1 {
  color: white;
  text-align: center;
}

footer {
  position: fixed;
  bottom: 0;
  width: 100%;
  margin-bottom: 15px;
}
</style>
