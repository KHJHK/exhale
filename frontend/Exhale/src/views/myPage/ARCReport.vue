<template>
  <div :style="{ fontSize: fontSize + 'px' }">

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
          :to="{ name: 'ARCReport' }"
          >마이페이지</RouterLink
        >
      </div>
     <button class="enlarge" @click="enlarge">
        <img src="@/assets/plus_white.png" class="plus">
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
          <div class="problem-data" v-for="item in select_problem_data_list" :key="item">
            <!-- {{ item.course_id }} -->
            <p class="date">날짜 : {{ item.start_of_week }} ~ {{ item.end_of_week }}</p>
            <p class="problemdetile">
              푼 문제 : <span>{{ item.correct_count + item.wrong_count }}</span
              >, 맞춘 문제 : <span>{{ item.correct_count }}</span> , 틀린 문제 :
              <span>{{ item.wrong_count }}</span>
            </p>
          </div>
        </div>
      </div>
      <div id="letter">
        <div id="letter-data-list">
          <div class="letter-data" v-for="(item, index) in letterDataList" :key="index">
           <div style="display: flex; flex-direction: column;">
             <p>{{ item.letter }}</p>
             <p>{{ item.type }}</p>
             <p>{{item.wrong_count / item.count * 100}} </p>  
            </div>
            
      
            
          </div>
        </div>
      </div>
    </div>


    <Footers_mypage class="footer1" />

  </div>

</div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import MyPageHeader from "@/components/common/MyPageHeader.vue";
import Footers_mypage from "@/components/common/Footers_mypage.vue";

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



const result1 = ref('')
const result2 = ref('')
const result3= ref('')
const result4= ref('')
const result5= ref('')







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
  result1.value =(letterDataList.value[0].wrong_count / letterDataList.value[0].count *100)
  result2.value =(letterDataList.value[1].wrong_count / letterDataList.value[1].count *100)
  result3.value =(letterDataList.value[2].wrong_count / letterDataList.value[2].count *100)
  result4.value =(letterDataList.value[3].wrong_count / letterDataList.value[3].count *100)
  result5.value =(letterDataList.value[4].wrong_count / letterDataList.value[4].count *100)

};

onMounted(() => {
  getProfile();
  getLetterData();
  getSolvedData();
});

const fontSize = ref(16);
const msg = computed(() => fontSize.value > 21 ? '원래대로' : '글자확대');
const enlarge = () => {
  fontSize.value ++;
  if (fontSize.value > 22) {
    fontSize.value = 16
  };
};


</script>

<style lang="scss" scoped>
@import "@/assets/scss/pages/_mainpage.scss";

* {
  font-family: 'NotoSansKR';
}

.footer1 {
  position: fixed;
  left: -8%;
  top: 125%;
  margin-top: -40vh;
}

.enlarge {
  color: white;
  border: 3px solid white;
  margin-right: 11vw;
}

.date {
  margin-top: 23px;
}

.problemdetile {

}

.problem-data {
  margin-left: 15px;
}

#page {
  height: 100vh;
  background-color: #6c9f9c;
  overflow: hidden;
  margin: 0;
}

//sidebar 관련
#sidebar {
  flex: 1;
  min-height: 80%;
  width: 30%;
  height: 80%;
  padding: 0% 1%;
  //background-color: rgb(169, 170, 170);
  position: absolute;
  left: 0;
  margin-bottom: -5vh;
  max-height: 100%;
  overflow: auto;
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
  min-height: 90%;
  width: 80%;
  height: 80%;
  //background-color: aquamarine;
  margin-left: 20%;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  margin-bottom: -5vh;
  max-height: 100%;
  overflow: auto;
} 

#solved {
  width: 50%;
  height: 50%;
  margin-bottom: 30vh;
  background-color: #F1F6F5;
  color: #334F4E;
  border: none;
  border-radius: 30px;
  padding: 2%;
}


#letter {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 25%;
  height: 50%;
  margin-bottom: 30vh;
  background-color: #F1F6F5;
  border: none;
  border-radius: 30px;
}

.data-title {
  margin-top: 20px;
  margin-bottom: 30px;
  font-weight: 700;
  text-align: center;
  font-size: 30px;
}

//solved-data 관련
#problem-category-list {
  display: flex;
}

.round-button {
  border: 3px solid rgb(201, 201, 201);
  color: gray;
  background-color: transparent;
  border-radius: 10px;
  font-size: 16px;
  padding: 10px 20px;
  margin: 0 10px;
  &:hover {
    color: white;
    background-color: #6C9F9C;
    border: 3px solid #6C9F9C;
  }
}

//letter-data 관련
#pie-chart {
  display: inline-block;
  position: absolute;
  width: 200px;
  height: 200px;
  background: conic-gradient(#334F4E 0% 25%, #6C9F9C 25% 56%, #A6D4D1 56% 100%);
  border-radius: 50%;
}

</style>
