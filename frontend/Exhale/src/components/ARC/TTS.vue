<template>
  <div>
    <button @click="speak">읽기</button>
  </div>
</template>

<script setup>
import { ref, defineProps, watch } from 'vue';
const props = defineProps({
  ttsText: String,
  });  

  const selectedLang = ref("ko-KR");
  const text = ref(props.ttsText);
  const isReading = ref(props.isReading);
  
  watch(props, () => {
    text.value = props.ttsText;
  }); 
  
  const emit = defineEmits(["update:isReading"]);
  watch(isReading, () => {
    emit('update:isReading', isReading.value);
  });


  function speak() {
    console.log("start")
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
