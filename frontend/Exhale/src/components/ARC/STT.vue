<template>
    <div class="speak">
      <div class="speakBtn">
        <button @click="enableStt" v-show="!sttRunning">
          <img src="@/assets/mic.svg" class="mic" >
        </button>
        <button @click="disableStt" v-show="sttRunning">
          <img src="@/assets/mic.svg" class="mic" >
        </button>
        <div class="message">{{ message }}</div>
      </div>
      <form @submit.prevent="onSubmit" ref="sttForm">
        <textarea class="textarea" name="sttText" v-model="sttText"></textarea>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref, watch, defineEmits } from 'vue';
  
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
      disableStt();
      sttText.value = transcript;
  };
  
  speechRecognition.onerror = (e) => {
      console.error(e);
      disableStt();
  };
  
  </script>
  
  <style lang="scss" scoped>

  .speak {
    position: fixed;
    top: 77%;
    left: 36%;
    color: rgb(108, 159, 156);
  }

  .mic {
    position: fixed;
    top: 70%;
    width: 70px;
    box-shadow: 2px 10px 15px 5px rgb(193, 193, 193);
    border-radius: 100%;
  }

  .message {
    position: fixed;
    left: 36.3%;
  }

  .textarea {
  position: fixed;
  top: 73%;
  left: 44%;
  width: 15%;
  height: 5%;
  }

  </style>
  