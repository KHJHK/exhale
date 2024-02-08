<script setup>
import { ref, onMounted, onBeforeUnmount  } from 'vue';
import { storeToRefs } from "pinia";
import { useAuthStore } from "@/stores/auth";
import { getCourseList, getProblem  } from '@/api/course.js';
import STT from '@/components/ARC/STT.vue';
import TTS from '@/components/ARC/TTS.vue';

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
const problem = {
  answer: ref(''),
  hint: ref(''),
  img_url : ref('')
};

const elapsedTime = ref(overTime);
let timerId;

// 컴포넌트가 마운트될 때 시작하는 타이머 설정
const startTimer = () => {
  timerId = setInterval(() => {
    elapsedTime.value--;
    if (elapsedTime.value < 0) {
      clearInterval(timerId);
      alert('타이머 종료');
    }
  }, 1000);
};

// 컴포넌트가 언마운트될 때 타이머 정리
const stopTimer = () => {
  clearInterval(timerId);
};


const saveProblem = async () => {
  try {
    const { data } = await getProblem(categoryId, token);
    if(data.dataStatus.code!=1){
      // todo api 응답 예외 처리
      return;
    }
    problemIdx = data.response.first_problem_index;
    problemSet = data.response.problemResponseList;

    problem.answer.value = problemSet[problemIdx].answer;
    problem.hint.value = problemSet[problemIdx].hint;
    problem.img_url.value = problemSet[problemIdx].img_url;

  } catch (error) {
    console.error(error); 
  }
};

// STT
const contentField = ref("")

const handleContentFieldChange = (value) => {
  contentField.value = value;
  // todo 음성 녹음 종료 시 정답 오답 처리
  nextProblem();
};

onMounted(startTimer);
onBeforeUnmount(stopTimer);


saveProblem();





// TTS
// const ttsText = ref("안녕하세요");

const nextProblem = () => {
  if(problemIdx>=problemSet.length){
    // todo 게임 종료
    console.log('게임 종료')
    return;
  }
  console.log(problemIdx)

  problem.answer.value = problemSet[problemIdx].answer;
  problem.hint.value = problemSet[problemIdx].hint;
  problem.img_url.value = problemSet[problemIdx].img_url;
  problemIdx++;
  no.value++;

}

</script>

<template>
    <div v-if="problem">
      <h1>경과 시간: {{ elapsedTime }}</h1>
      <div>{{ no }}. 아래 이미지가 나타내는 적합한 단어를 말하세요. </div>
      <STT 
        @update:modelValue="handleContentFieldChange"
        />
      <div>{{ problem.answer.value }}</div>
      <div>{{ problem.hint.value }}</div>
      <div><img :src="problem.img_url.value"/></div>
      <!-- <button @click="nextProblem">다음</button> -->
      <!-- <TTS
        :tts-text="ttsText"
      /> -->
    </div>
</template>


<style lang="scss" scoped>

</style>