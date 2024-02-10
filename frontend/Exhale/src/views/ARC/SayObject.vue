<script setup>
import { ref, onMounted, onBeforeUnmount  } from 'vue';
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
// const token = authStore.JWTtoken;
const token ='Bearer eyJhbGciOiJIUzI1NiJ9.eyJsb2dpbl9pZCI6InNzYWZ5MTAwIiwibWVtYmVyX2lkIjo1LCJyb2xlIjoiUk9MRV9VU0VSIiwiaWF0IjoxNzA3NTUyNTg1LCJleHAiOjE3MDc1NTQzODV9.7p9kgcv1nQzDhKVQ9wehq6LJQVAqQ5DGcF6KL5QLV5U'
let problemIdx=0;
let problemSet=null;
let no = ref(1);

// 다이어로그
const resultDialog = ref(false);
const isRight = ref(false);
const againTick = ref(false);
const reviewTick = ref(false);
const isPause = ref(false);
const isReturn = ref(false);
const isComplete = ref(false);

const sttText = ref(".");

const problem = {
  problemId : ref(''),
  answer: ref(''),
  hint: ref(''),
  imgUrl : ref('')
};

// 타이머
const elapsedTime = ref(overTime);
let timerId;

// 컴포넌트가 마운트될 때 시작하는 타이머 설정
const startTimer = () => {

  timerId = setInterval(() => {
    elapsedTime.value--;
    if (elapsedTime.value < 0) {
      elapsedTime.value = 0;
      resultProcessing("");
    }
  }, 1000);
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
    const { data } = await postReview(problem.problemId.value, token);
    if(data.dataStatus.code==2){
      // todo api 응답 예외 처리
      return;
    }else if(data.dataStatus.code!=1){
    }
    isComplete.value = true;
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
    isComplete.value = true;
    return;
  }
  resultDialog.value = false;

  problemIdx++;
  problem.problemId.value = problemSet[problemIdx].problem_id;
  problem.answer.value = problemSet[problemIdx].answer;
  problem.hint.value = problemSet[problemIdx].hint;
  problem.imgUrl.value = problemSet[problemIdx].img_url;
  no.value++;

  // 초기화
  stopTimer();
  elapsedTime.value = overTime;
  startTimer();  
}

const resultProcessing = (text) =>{
  clearInterval(timerId);
  let _isRight = false;
  if(text!="" && text == problem.answer.value){  // 정답
    _isRight = true;
  }else{  // 오답
    _isRight = false;
  }
  isRight.value = _isRight;
  resultDialog.value = true;

  
  saveSolvedProblem();

}

const handleSttTextChange = (text) => {
  // todo sttText 반영 안되는 오류
  sttText.value = ".";
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
  nextProblem();
};
const handleReviewTickChange = (value) => {
  stopTimer();
  saveReviewProblem();
  reviewTick.value = value;
  
};
const handleAgainTickChange = (value) => {
  stopTimer();
  elapsedTime.value = overTime;
  againTick.value = false;
  resultDialog.value = false;
  startTimer();  
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
const handleIsExitChange = (value) => {
  stopTimer();  
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

</script>

<template>
    <div v-if="problem">
      <div >
        <ResultDialog 
          :dialog = "resultDialog"
          :isRight = "isRight"  
          :reviewTick = "reviewTick"
          :againTick = "againTick"
          :isPause = "isPause"
          :isReturn = "isReturn"  
          :isComplete="isComplete"
          @update:dialog="handleDialogChange"
          @update:nextTick="handleNextTickChange"
          @update:reviewTick="handleReviewTickChange"
          @update:againTick="handleAgainTickChange"
          @update:isClose="handleIsCloseChange"
          @update:isPause="handleIsPauseChange"
          @update:isExit="handleIsExitChange"
          @update:isReturn="handleIsReturnChange"
          
          />
      </div>

      <h1>경과 시간: {{ elapsedTime }}</h1>
      <div>{{ no }}. 아래 이미지가 나타내는 적합한 단어를 말하세요. </div>
      <STT 
        :sttText="sttText"
        @update:sttText="handleSttTextChange"
        />
      <div>{{ problem.answer.value }}</div>
      <div>{{ problem.hint.value }}</div>
      <div><img :src="problem.imgUrl.value"/></div>
      <!-- <TTS
        :tts-text="ttsText"
      /> -->
    </div>
</template>


<style lang="scss" scoped>

</style>