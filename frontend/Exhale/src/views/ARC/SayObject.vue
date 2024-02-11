<script setup>
import { ref, onMounted, onBeforeUnmount, computed  } from 'vue';
import { storeToRefs } from "pinia";
import { useAuthStore } from "@/stores/auth";
import { getProblem, postSolvedProblem, postReview  } from '@/api/course.js';
import STT from '@/components/ARC/STT.vue';
// import TTS from '@/components/ARC/TTS.vue';
import ResultDialog from '@/components/ARC/ResultDialog.vue'

// todo routing으로 받기
const courseName='';
const categoryName='';
const overTime = 10;
const categoryId = 1; 

const authStore = useAuthStore();
const { JWTtoken } = storeToRefs(authStore);
const token = JWTtoken;

let problemIdx=0;
let problemSet=null;
let no = ref(1);

// 다이어로그
const resultDialog = ref(false);
const isRight = ref(false);
const isPause = ref(false);
const isReturn = ref(false);

const problem = {
  problemId : ref(''),
  answer: ref(''),
  hint: ref(''),
  imgUrl : ref('')
};

// 타이머
const elapsedTime = ref(overTime);
let timerId;
const timerWidth = computed(() => (elapsedTime.value / overTime) * 100);


// 컴포넌트가 마운트될 때 시작하는 타이머 설정
const startTimer = () => {
  timerId = setInterval(() => {
    elapsedTime.value--;
    if (elapsedTime.value < 0) {
      elapsedTime.value = 0;
      resultProcessing("");
    }
  }, 100000);
};

// 컴포넌트가 언마운트될 때 타이머 정리
const stopTimer = () => {
  clearInterval(timerId);
};


const getProblems = async () => {
  try {
    const { data } = await getProblem(categoryId, token);
    if(data.dataStatus.code!=1){
      // todo api 응답 예외 처리
      return;
    }
    problemIdx = data.response.first_problem_index;
    problemSet = data.response.problemResponseList;

    problem.problemId.value = problemSet[problemIdx].problem_id;
    problem.answer.value = problemSet[problemIdx].answer;
    problem.hint.value = problemSet[problemIdx].hint;
    problem.imgUrl.value = problemSet[problemIdx].img_url;

  } catch (error) {
    console.error(error); 
  }
};
const saveSolvedProblem = async() => {

  try{
    const params = {
      problemId: problem.problemId.value,
      isRight: isRight.value,
      time : overTime - elapsedTime.value
    }
    const {data} = await postSolvedProblem(params, token);
    if(data.dataStatus.code!=1){
      // todo api 응답 예외 처리
      return;
    }
  } catch(error){
    console.error(error); 
  }
}

const saveReviewProblem = async () => {
  try {
    const { data } = await postReview(problem.problemId.value);
    if(data.dataStatus.code==2){
      // todo api 응답 예외 처리
      return;
    }else if(data.dataStatus.code!=1){
    }
    alert("저장 완료");

  } catch (error) {
    if(error.response.data.dataStatus==4){
      console.log("이미 저장된 문제입니다.");
    }
    console.error(error);

  }
};

// TTS
// const ttsText = ref("안녕하세요");

const nextProblem = () => {

  if(problemIdx>=problemSet.length-1){
    // todo 게임 종료
    console.log('게임 종료')
    return;
  }
  
  problemIdx++;
  problem.problemId.value = problemSet[problemIdx].problem_id;
  problem.answer.value = problemSet[problemIdx].answer;
  problem.hint.value = problemSet[problemIdx].hint;
  problem.imgUrl.value = problemSet[problemIdx].img_url;
  no.value++;


  // 초기화
  elapsedTime.value = overTime;
  startTimer();  
}

const resultProcessing = (sttText) =>{
  clearInterval(timerId);

  let _isRight = false;
  if(sttText == problem.answer.value){  // 정답
    _isRight = true;
  }else{  // 오답
    _isRight = false;
  }
  isRight.value = _isRight;
  resultDialog.value = true;
  saveSolvedProblem();
 

}

const handleContentFieldChange = (text) => {
  resultProcessing(text);
};
const handleDialogChange = (value) => {
  resultDialog.value = value;
  if(!value){
    isPause.value = false;
    isReturn.value=false;
  }

};
const handleNextTickChange = (value) => {
  stopTimer();
  nextProblem();
  resultDialog.value = false;
};
const handleReviewTickChange = (value) => {
  stopTimer();
  saveReviewProblem();
};
const handleAgainTickChange = (value) => {
  stopTimer();
  elapsedTime.value = overTime;
  startTimer();  
  resultDialog.value = false;
};
const handleIsCloseChange = (value) => {
  stopTimer();
  window.close();
};
const handleIsPauseChange = (value) => {
  
  stopTimer();
  resultDialog.value = true;
  isPause.value = value;
  isReturn.value = false;
};

const handleIsReturnChange = (value) => {
  startTimer();  
  
  resultDialog.value = false;
  isPause.value = false;
  isReturn.value = false;

};

onMounted(startTimer);
onBeforeUnmount(stopTimer);


getProblems();


const fontSize = ref(16);
const msg = computed(() => fontSize.value > 21 ? '원래대로' : '글자확대');
const enlarge = () => {
  fontSize.value ++;
  if (fontSize.value > 22) {
    fontSize.value = 16
  };
};


</script>

<template>
      
<div :style="{ fontSize: fontSize + 'px' }">

  <div class="background">


    <img src="@/assets/logo_green.png" alt="logo" class="navbar-logo" >

    <section class="sub-nav1">
        <div id="breadcrum">
          <RouterLink class="breadlink" :to="{name: 'MainPage'}">메인 홈</RouterLink>
          >
          <RouterLink class="breadlink" :to="{name: 'PostWholeListView'}">커뮤니티</RouterLink>
          >
          <RouterLink class="breadlink" :to="{name: 'PostWholeListView'}">전체</RouterLink>
        </div>
        <button class="enlarge" @click="enlarge" style="position: fixed; right: 0px; z-index: 10;">
        <img src="@/assets/plus.svg" class="plus">
        {{ msg }}
        </button> 
    </section>


    

    <div class="problem" v-if="problem">
      <div >
        <ResultDialog 
          :dialog = "resultDialog"
          :isRight = "isRight"  
          :isPause = "isPause"
          :isReturn = "isReturn"  
          @update:dialog="handleDialogChange"
          @update:nextTick="handleNextTickChange"
          @update:reviewTick="handleReviewTickChange"
          @update:againTick="handleAgainTickChange"
          @update:isClose="handleIsCloseChange"
          @update:isPause="handleIsPauseChange"
          @update:isReturn="handleIsReturnChange"

          />
      </div>

        <div class="timer">
          <div class="timer-bar" :style="{ width: timerWidth + '%' }">
            <img src="@/assets/clock1.svg" class="clock">
          </div>
          <!-- <h1>경과 시간: {{ elapsedTime }}</h1> -->
        </div>

        <div class="content">
            <div>{{ no }}. &nbsp; &nbsp; 아래 이미지가 나타내는 적합한 단어를 말하세요. </div>
            <STT 
              @update:modelValue="handleContentFieldChange"
              />
              <div><img class="imgurl" :src="problem.imgUrl.value"/></div>
              <div class="answer">{{ problem.answer.value }}</div>
              <div class="hint">{{ problem.hint.value }}</div>
        </div>
        <!-- <button @click="nextProblem">다음</button> -->
        <!-- <TTS
          :tts-text="ttsText"
        /> -->
    </div>


  </div>

</div>
</template>


<style lang="scss" scoped>
@import '@/assets/scss/layout/gamebackground.scss'
</style>