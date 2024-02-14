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
      <div id="solved">
        <p class="data-title">문제 풀이 통계</p>
        <div id="problem-category-list">
          <button class="round-button" @click="select_button(1)">
            이름 대기
          </button>
          <button class="round-button" @click="select_button(2)">
            따라 말하기
          </button>
          <button class="round-button" @click="select_button(3)">
            듣기 이해력
          </button>
          <button class="round-button" @click="select_button(4)">유창성</button>
        </div>
        <div id="problem-data-list">
          <div class="problem-data" v-for="item in select_problem_data_list">
            {{ item.course_id }}
            <p>날짜 : {{ item.start_of_week }} ~ {{ item.end_of_week }}</p>
            <p>
              푼 문제 : <span>{{ item.correct_count + item.wrong_count }}</span
              >, 맞춘 문제 : <span>{{ item.correct_count }}</span> , 틀린 문제 :
              <span>{{ item.wrong_count }}</span>
            </p>
          </div>
        </div>
      </div>
      <div id="letter">
        <div id="pie-chart"></div>
        <div id="letter-data-list">
          <div class="letter-data" v-for="item in letterDataList">
            {{ item }}
          </div>
        </div>
      </div>
    </div>
    <Footers />
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import MyPageHeader from "@/components/common/MyPageHeader.vue";
import Footers from "@/components/common/Footers.vue";

import * as mypage from "@/api/mypage";
import * as report from "@/api/report";
import { storeToRefs } from "pinia";
import { useAuthStore } from "@/stores/auth";

const authStore = useAuthStore();
const { jwtToken } = storeToRefs(authStore);
const token = jwtToken.value;

const nickname = ref("");
const image = ref("");
const letterDataList = ref([]);
const solvedDataList = ref([]);

const select_course_id = ref(4);
const select_button = (course_id) => {
  select_course_id.value = course_id;
};

const select_problem_data_list = computed(() => {
  var alist = solvedDataList.value.filter(
    (item) => item.course_id === select_course_id.value
  );
  console.log(alist);
  return alist;
});

//API 호출
const getProfile = async () => {
  const response = await mypage.getProfile(token);
  nickname.value = response.data.response.nickname;
  image.value = response.data.response.image_url;
};

const getSolvedData = async () => {
  const response = await report.getSolvedData(token);
  solvedDataList.value = response.data.response;
};

const getLetterData = async () => {
  const response = await report.getLetterData(token);
  letterDataList.value = response.data.response;
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
  width: 20%;
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
  width: 80%;
  //background-color: aquamarine;
  margin-left: 20%;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

#solved {
  width: 40%;
  height: 600px;
  background-color: white;
}

#letter {
  width: 40%;
  height: 600px;
  background-color: white;
}

.data-title {
  margin-top: 35px;
  text-align: center;
  font-size: 30px;
}

//solved-data 관련
#problem-category-list {
  display: flex;
}

.round-button {
  border: 2px solid #555;
  background-color: #fff;
  font-size: 16px;
  padding: 10px 20px;
  margin: 0 10px;
}

//letter-data 관련
#pie-chart {
  display: inline-block;
  position: relative;
  width: 200px;
  height: 200px;
  background: conic-gradient(#8b22ff 0% 25%, #ffc33b 25% 56%, #21f3d6 56% 100%);
  border-radius: 50%;
}
</style>
