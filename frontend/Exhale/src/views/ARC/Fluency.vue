<script setup>
import { ref, onMounted, onBeforeUnmount, computed, nextTick  } from 'vue';
import { storeToRefs } from "pinia";
import { useAuthStore } from "@/stores/auth";
import { getProblem, postSolvedProblem, postReview, checkfluencyAnswer  } from '@/api/course.js';
import STT from '@/components/ARC/STT.vue';
import TTS from '@/components/ARC/TTS.vue';
import ResultDialog from '@/components/ARC/ResultDialog.vue'


// todo routing으로 받기
const courseName='';
const categoryName='';
const overTime = 10;
const categoryId = 8; 

const authStore = useAuthStore();
const { JWTtoken } = storeToRefs(authStore);
const token = JWTtoken;

let problemIdx=0;
let problemSet=null;

// 다이어로그
const resultDialog = ref(false);
const isRight = ref(false);
const againTick = ref(false);
const reviewTick = ref(false);
const isPause = ref(false);
const isReturn = ref(false);
const isComplete = ref(false);

const sttText = ref("");
const question = ref("");
const isReading = ref(false);
let isFirst = true;
let isExplain = false;

const problem = {
  problemId : ref(0),
  question: ref(''),
  explain: ref(''),
};


//타이머
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
    problem.question.value = problemSet[problemIdx].question;
    question.value = problem.question.value;
    clickTTSQustion();

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


const nextProblem = () => {

  if(problemIdx>=problemSet.length-1){
    // todo 게임 종료
    isComplete.value = true;
    return;
  }
  resultDialog.value = false;

  problemIdx++;
  problem.problemId.value = problemSet[problemIdx].problem_id;
  problem.question.value = problemSet[problemIdx].question;
  question.value = problem.question.value;

  // 초기화
  clearInterval(timerId);
  elapsedTime.value = overTime;
}

const resultProcessing = async (text) =>{
  clearInterval(timerId);
  let _isRight = false;
  if(text != ""){
    const params = {
        question : question.value,
        answer : text
    };
    const {data} = await checkfluencyAnswer(params, token);
    if(data.dataStatus.code!=1){
        // todo api 응답 예외 처리
        return;
    }
    _isRight = data.response.result;
    problem.explain.value = data.response.explain;
    clickTTSAnswer();
  }else{
    _isRight = false;
    resultDialog.value = true;
  }
  isRight.value = _isRight;
  isExplain = true;

  
  saveSolvedProblem();
  

}

const handleSttTextChange = (text) => {
  // todo sttText 반영 안되는 오류
  resultProcessing(text);
};
const handleIsReadingChange = (value) => {
    if(isFirst && !value){
        isFirst = false;
        startTimer();
    }else if(isExplain && !value){
        resultDialog.value = true;
        isExplain = false;
    }
  isReading.value = value;
};


const handleDialogChange = (value) => {
  resultDialog.value = value;
  if(!value){
    isPause.value = false;
    isReturn.value=false;
    problem.explain.value = '';
  }

};
const handleNextTickChange = (value) => {
  isFirst = true;
  nextProblem();
  clickTTSQustion();

};
const handleReviewTickChange = (value) => {
  stopTimer();
  saveReviewProblem();
  reviewTick.value = value;
  
};
const handleAgainTickChange = (value) => {
  isFirst = true;
  elapsedTime.value = overTime;
  againTick.value = false;
  resultDialog.value = false;
  problem.explain.value = '';
  clickTTSQustion();
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

const clickTTSQustion = async () => {
  await nextTick(); 
  const ttsButton = document.querySelector('#question > div > #tts-button');
  if (ttsButton) {
    ttsButton.click();
  }
};
const clickTTSAnswer = async () => {
  await nextTick(); 
  const ttsButton = document.querySelector('#answer > div > #tts-button');
  if (ttsButton) {
    ttsButton.click();
  }
};

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
          메인 홈&nbsp; &nbsp;>&nbsp;&nbsp; 언어재활코스 &nbsp; &nbsp;>&nbsp; &nbsp;유창성
        </div>
        <button class="enlarge" @click="enlarge" style="position: fixed; right: 0px; z-index: 10;">
        <img src="@/assets/plus.svg" class="plus">
        {{ msg }}
        </button> 
    </section>

    <div class="problem2" v-if="problem">
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


      <div class="timer">
          <div class="timer-bar" :style="{ width: timerWidth + '%' }">
            <img src="@/assets/clock1.svg" class="clock">
          </div>

        </div>

        <div class="question">
          {{ question }}
        </div>
        <img src="@/assets/triangle_left.svg" class="triangle_left">
        
        <div class="sttText">
          {{ sttText }}
        </div>
        <img src="@/assets/triangle.svg" class="triangle">
      
            <div :class="isReading ? 'stt-able' :  'stt-disable'">
                <STT 
                :sttText="sttText"
                @update:sttText="handleSttTextChange"
                />
            </div>

        <div id="question" >
        <TTS
            :text="question"
            :isReading="isReading"
            :showButton="true"
            @update:isReading="handleIsReadingChange"
            />
          <!-- <img src="@/assets/headphone.svg" id="tts-button" alt=""> -->
        </div>
        <div id="answer">
            <TTS
            :text="problem.explain.value"
            :isReading="isReading"
            :showButton="false"
            @update:isReading="handleIsReadingChange"
            />
        </div>
        <div class="answerText">답 : {{ problem.explain.value }}</div>
        <!-- <button @click="click">클릭</button> -->
    </div>


    <img class="cloud-character" src="@/assets/character.png" alt="">

  </div>

</div>
</template>


<style lang="scss" scoped>

.sub-nav1 {
  margin-top: 0;
  position: fixed;
  top: 19%;
  left: 6%;
  font-family: 'NotoSansKR';
  font-size: 15px;
  color: rgb(153, 153, 153);
  // letter-spacing: 3%;
}

.enlarge {
  position: fixed;
  top: 64px;
  left: 1290px;
  width: 117px;
  height: 40px;
  padding: -5px 0px;
  font-size: 18px;
  border-radius: 30px;
  border: 3px solid rgb(175, 175, 175); 
  &:hover {
    border: rgb(108, 159, 156) solid 3px;
    & .plus {
      width: 0px;
    }
}
  & .plus {
    width: 21px;
  }
}

.background {
  width: 100vw;
  height: 100vh;
  background: rgb(108,159,156);
  background: radial-gradient(circle, rgba(108,159,156,0.9506653002998074) 0%, rgba(255,255,255,1) 50%);
  z-index: -1;
}

.navbar-logo {
  width: 8%;
  position: fixed;
  top: 27px;
  left: 75px;
  // z-index: 2;
}

.problem {
  display: flex;
  align-items: center;
  flex-direction: column;
}

.timer {
  position: fixed;
  top: 8.5%;
  left: 34.2%;
  width: 35%;
  height: 40px;
  background-color: #ffffff;
  border-radius: 30px;
  overflow: hidden;
}

.timer-bar {
  position: relative;
  height: 100%;
  background-color: rgb(108, 159, 156);
  transition: width 1s linear;
}

.clock {
  position: absolute;
  top: 9px;
  left: -410px;
  width: 900px;
  height: 23px;
}

.content {
  position: fixed;
  top: 19%;
  font-size: 120%;
  color: rgb(69, 69, 69);
  font-family: 'NotoSansKR';
  letter-spacing: 1px;
  & .problemtitle {
    width: 120%;
  }
}

.imgurl {
  position: fixed;
  top: 28%;
  left: 35%;
  width: 500px;
  height: 300px;
  object-fit: cover;
  border-radius: 20px;
}

.answer {
  position: fixed;
  top: 80%;
  left: 50%;
}

.hint {
  position: fixed;
  top: 86%;
  left: 49.5%;
  color: rgb(155, 155, 155);
}

.hintBtn {
  font-size: 86%;
  position: fixed;
  top: 78%;
  left: 63.5%;
  color: white;
  border: 3px solid white;
  border-radius: 25px;
  padding-top: 4px;
  padding-bottom: 8px;
  padding-left: 15px;
  padding-right: 15px;
  text-shadow: 5px 2px 7px rgb(171, 171, 171);
  box-shadow: 1px 2px 5px rgb(211, 211, 211);
}


//////////<STT.vue>////////

// .speak {
//   position: fixed;
//   top: 77%;
//   left: 36%;
//   color: rgb(108, 159, 156);
// }

.mic {
  position: fixed;
  top: 77%;
  left: 35%;
  width: 60px;
  box-shadow: 1px 5px 4px 3px rgb(193, 193, 193);
  border-radius: 100%;
}

.backcircle {
  position: fixed;
  top: 77%;
  left: 35%;
  width: 60px;
  box-shadow: 1px 2px 10px 3px rgb(255, 255, 255);
  border-radius: 100%;
  z-index: -1;
}

.message {
  position: fixed;
  top: 86.5%;
  left: 37.2%;
  transform: translateX(-50%);
  font-size: 85%;
  opacity: 70%;
}


.textarea {
  width: 24%;
  margin-left: 37.7%;
  margin-top: 20%;
  border-radius: 10px;
  padding: 15px 17px;
  background-color: rgb(230, 230, 230);
  text-align: center;
  color: rgb(45, 45, 45);
  &:focus {
    outline: none;
  }
}

.volume {
  position: fixed;
  top: 90%;
  width: 70px;
}

.soundwave {
  position: fixed;
  top: 88%;
  left: 54%;
  transform: translate(-50%, -50%);
  width: 30%;
  max-width: 1000px;
  // z-index: 50;
}

.numbering {
  font-size: 150%;
  margin-left: 2%;
}



.stt-able{
    pointer-events: none;
    // background-color : rgba(128, 128, 128, 0.8);
}

.stt-disable{
    pointer-events: cursor;
    // background-color: '';
}

// #question2 {
//   // margin-left: 700%;
//   // margin-top: 300%;
//   width: 60px;
//   box-shadow: 1px 5px 4px 3px rgb(193, 193, 193);
//   border-radius: 100%;
//   z-index: 20;
// }

.cloud-character {
  position: fixed;
  top: 43%;
  left: 65%;
  width: 90px;
}

.question {
  position: fixed;
  top: 25%;
  left: 41%;
  border-radius: 10px;
  text-align: center;
  padding: 15px 17px;
  background-color: rgb(255, 255, 255);
  color: rgb(45, 45, 45);
  width: 29%;
  font-family: 'NotoSansKR';
  letter-spacing: 1px;
}

.triangle {
  position: fixed;
  top: 47%;
  left: 62.2%;
  width: 20px;
}

.triangle_left {
  position: fixed;
  top: 27%;
  left: 40%;
  width: 20px;
}

#answer {
  position: fixed;
  top: 85%;
  left: 50%;
}

.answerText {
  position: fixed;
  top: 90%;
  left: 50%;
}

.sttText {
  width: 40%;
  height: 20%;
  text-align: center;
  &:focus {
    outline: none;
  }
}



</style>