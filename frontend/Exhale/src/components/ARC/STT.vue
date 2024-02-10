<template>
    <div>
      <button @click="enableStt" v-show="!sttRunning">마이크</button>
      <button @click="disableStt" v-show="sttRunning">마이크</button>
      <div>{{ message }}</div>
    </div>
  </template>
  
  <script setup>
  import { ref, watch, defineEmits, defineProps } from 'vue';
  const props = defineProps({
    sttText: String,
  });
  const sttText = ref(props.sttText);
  watch(props, () => {
    sttText.value = props.sttText;
  });
  

  const emit = defineEmits(["update:sttText"]);
  
  watch(sttText, () => {
    emit('update:sttText', sttText.value);
  });
  
  const message = ref("Click!"); // 초기값 변경
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

  
  speechRecognition.onresult = (e) => {

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
  
  <style scoped>
  </style>
  