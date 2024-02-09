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
const { JWTtoken } = storeToRefs(authStore);
const token = JWTtoken.value;

let problemIdx=0;
let problemSet=null;
let no = ref(1);

// 다이어로그
const resultDialog = ref(false);
const isRight = ref(false);

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
    console.log(data)
    problemIdx = data.response.first_problem_index;
    problemSet = data.response.problemResponseList;

    problem.problemId.value = problemSet[problemIdx].problem_id;
    problem.answer.value = problemSet[problemIdx].answer;
    problem.hint.value = problemSet[problemIdx].hint;
    problem.imgUrl.value = problemSet[problemIdx].img_url;
    console.log( problem.problemId.value )

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
      console.log("문제 저장 예외",data);
      return;
    }
    console.log("문제 저장 예외 아님",data);  
  } catch(error){
    console.error(error); 
  }
}
// STT

const saveReviewProblem = async () => {
  try {
    console.log("pid",problem.problemId.value)
    const { data } = await postReview(problem.problemId.value);
    if(data.dataStatus.code==2){
      // todo api 응답 예외 처리
      console.log("이미 저장된 문제입니다.")
      return;
    }else if(data.dataStatus.code!=1){
      console.log("복습 예외 발생")
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
  console.log(problemIdx)

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
  saveSolvedProblem();
 
  isRight.value = _isRight;
  resultDialog.value = true;

}

const handleContentFieldChange = (text) => {
  resultProcessing(text);
};


const handleResultDialogChange = (value) => {
  resultDialog.value = false;
};

const handleNextTickChange = (value) => {
  stopTimer();
  nextProblem();
};
const handleReviewTickChange = (value) => {
  stopTimer();
  saveReviewProblem();
  console.log("복습")
};
const handleAgainTickChange = (value) => {
  stopTimer();
  elapsedTime.value = overTime;
  startTimer();  
};


onMounted(startTimer);
onBeforeUnmount(stopTimer);


getProblems();

</script>

<template>
    <div v-if="problem">
      <div v-if="resultDialog">
        <ResultDialog 
          :dialog = "resultDialog"
          :isRight = "isRight"      
          @update:dialog="handleResultDialogChange"
          @update:nextTick="handleNextTickChange"
          @update:reviewTick="handleReviewTickChange"
          @update:againTick="handleAgainTickChange"
          />
      </div>
      <h1>경과 시간: {{ elapsedTime }}</h1>
      <div>{{ no }}. 아래 이미지가 나타내는 적합한 단어를 말하세요. </div>
      <STT 
        @update:modelValue="handleContentFieldChange"
        />
      <div>{{ problem.answer.value }}</div>
      <div>{{ problem.hint.value }}</div>
      <div><img :src="problem.imgUrl.value"/></div>
      <!-- <button @click="nextProblem">다음</button> -->
      <!-- <TTS
        :tts-text="ttsText"
      /> -->
    </div>
</template>


<style lang="scss" scoped>

</style>