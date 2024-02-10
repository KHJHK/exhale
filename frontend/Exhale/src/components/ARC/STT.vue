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
  