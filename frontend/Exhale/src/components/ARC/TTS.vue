<template>
  <div>
    <button @click="speak">
      <img v-if="showButton" src="@/assets/headphone.svg" id="tts-button" alt="">
      <span v-else>정답 확인</span>
    </button>
      <img src="@/assets/headphone.svg" class="tts-button2" alt="">
    <!-- <img src="@/assets/mic.svg" class="backcircle" > -->
  </div>
</template>

<script setup>
import { ref, defineProps, watch} from 'vue';
const props = defineProps({
  text: String,
  showButton: {
    type: Boolean,
    default: true  // 이미지 버튼 기본적으로 표시
  }
  });  

  const selectedLang = ref("ko-KR");
  const text = ref(props.text);
  const isReading = ref(props.isReading);
  
  watch(() => props.text, () => {
    text.value = props.text;
  }); 
  
  const emit = defineEmits(["update:isReading"]);
  watch(isReading, () => {
    emit('update:isReading', isReading.value);
  });


  function speak() {
    isReading.value = true;
  if (typeof SpeechSynthesisUtterance === "undefined" || typeof window.speechSynthesis === "undefined") {
    alert("이 브라우저는 음성 합성을 지원하지 않습니다.")
    return
  }
  
  window.speechSynthesis.cancel(); // 현재 읽고있다면 초기화

  const speechMsg = new SpeechSynthesisUtterance();
  speechMsg.rate = 1; // 속도: 0.1 ~ 10      
  speechMsg.pitch = 1.2; // 음높이: 0 ~ 2
  speechMsg.lang = selectedLang.value;
  speechMsg.text = text.value;

  speechMsg.onend = function() {
    isReading.value = false;
  };

  // SpeechSynthesisUtterance에 저장된 내용을 바탕으로 음성합성 실행
  window.speechSynthesis.speak(speechMsg);
}
</script>

<style lang="scss" scoped>

#tts-button {
  position: fixed;
  top: 25%;
  left: 33%;
  box-shadow: 1px 5px 4px 3px rgb(170, 169, 169);
  border-radius: 100%;
  width: 60px;
  z-index: 2;
}

.tts-button2 {
  position: fixed;
  top: 25%;
  left: 33%;
  width: 60px;
  box-shadow: 1px -5px 10px -1px rgb(255, 255, 255);
  border-radius: 100%;
  z-index: -1;
}

</style>