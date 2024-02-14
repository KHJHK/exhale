<template>
  <div class="parent">
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
        </div>
      </div>
    </header>

    <main>
      <div class="info">
        <div class="profile-image"><img :src="image" /></div>
      </div>
      <div class="report">
        <h1>대단해요! {{ nickname }}님이 푼 문제는 총 128문제에요.</h1>
      </div>
    </main>

    <footer>
      <Footers />
    </footer>
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
