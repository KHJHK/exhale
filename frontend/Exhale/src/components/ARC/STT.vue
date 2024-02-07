<template>
    <div>
      <button @click="enableStt" v-show="!sttRunning">마이크</button>
      <button @click="disableStt" v-show="sttRunning">마이크</button>
      <div>{{ message }}</div>
      <form @submit.prevent="onSubmit" ref="sttForm">
        <textarea name="sttText" v-model="sttText"></textarea>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref, watch, defineEmits, defineProps } from 'vue';
  
  // props로 전달된 sttText를 자식 컴포넌트 내부에서 사용하기 위해 ref로 감싸줍니다.
  const sttText = ref("");
  
  // textarea에 입력된 값을 sttText에 반영하는 함수
  const updateSttText = (event) => {
    sttText.value = event.target.value;
  };
  const emit = defineEmits(["update:modelValue"]);
  
  // sttText 데이터가 변경될 때마다 부모 컴포넌트로 변경된 값을 emit하여 전달합니다.
  watch(sttText, () => {
    emit('update:modelValue', sttText.value);
  });
  
  const message = ref("Click!"); // 초기값 변경
  const sttFormRef = ref(null); // 이름 변경
  let sttTextValue = "";
  
  const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
  const speechRecognition = new SpeechRecognition();
  speechRecognition.lang = "ko-kr";
  speechRecognition.continuous = true;
  
  const sttRunning = ref(false);
  
  const enableStt = () => {
      sttTextValue = sttText.value;
      speechRecognition.start();
      message.value = "지금 듣고 있어요!";
      sttRunning.value = true;
  };
  
  const disableStt = () => {
      speechRecognition.stop();
      message.value = "Click!";
      sttRunning.value = false;
  };
  
  const onSubmit = () => {
      enableStt();
  };
  
  speechRecognition.onresult = (e) => {
      console.log(e.results);
      const transcript = Array.from(e.results)
          .map(result => result[0])
          .map(speech => speech.transcript)
          .join(' ');
      console.log("transcript :", transcript);
      sttText.value = sttTextValue + " " + transcript;
  };
  
  speechRecognition.onerror = (e) => {
      console.error(e);
      disableStt();
  };
  
  </script>
  
  <style scoped>
  /* 필요한 스타일을 작성하세요 */
  </style>
  