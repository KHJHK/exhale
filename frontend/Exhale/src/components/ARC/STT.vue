<template>
  <div class="speak">
    <div class="speakBtn">
      <button @click="enableStt" v-show="!sttRunning">
        <img src="@/assets/mic.svg" class="mic" >
      </button>
      <button @click="disableStt" v-show="sttRunning">
        <img src="@/assets/mic.svg" class="mic" >
      </button>
      <img src="@/assets/mic.svg" class="backcircle" >
      <SoundWave :volume="volume" class="soundwave" />
      <div class="message">{{ message }}</div>
      <!-- <div class="volume">Volume: {{ volume }}</div> -->
    </div>
    <form @submit.prevent="onSubmit" ref="sttForm">
      <input type="text" class="textarea" :value="props.modelValue" @input="updateSttText">

    </form>
  </div>
</template>

<script setup>
import { ref, watch, defineProps, defineEmits, onMounted, onUnmounted } from 'vue';
import SoundWave from './SoundWave.vue';
const sttText = ref(''); 
const emit = defineEmits(["update:modelValue"]);

const props = defineProps({
  modelValue: String
});


// props의 modelValue가 변경될 때마다 이벤트를 발생시킵니다.
watch(() => props.modelValue, (newValue) => {
  emit("update:modelValue", newValue);
});

// textarea에 입력된 값을 sttText에 반영하는 함수
const updateSttText = (event) => {
  emit('update:modelValue', event.target.value);
};

// sttText 데이터가 변경될 때마다 부모 컴포넌트로 변경된 값을 emit하여 전달합니다.
// watch(sttText, () => {
//   emit('update:modelValue', sttText.value);
// });

const message = ref("Click!"); // 초기값 변경
const volume = ref(0); // 볼륨 상태
const sttFormRef = ref(null); // 이름 변경
let sttTextValue = "";
let audioContext = null;
let analyser = null;
let microphone = null;
let javascriptNode = null;

const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
const speechRecognition = new SpeechRecognition();
speechRecognition.lang = "ko-kr";
speechRecognition.continuous = true;

const sttRunning = ref(false);

const enableStt = () => {
    sttTextValue = props.modelValue;
    speechRecognition.start();
    message.value = "지금 듣고 있어요!";
    sttRunning.value = true;
    startVolumeMonitoring();
    
};

const disableStt = () => {
    speechRecognition.stop();
    message.value = "Click!";
    sttRunning.value = false;
    stopVolumeMonitoring();
    
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
    emit('update:modelValue', transcript);
    disableStt();
    sttText.value = transcript;
   
};

speechRecognition.onerror = (e) => {
    console.error(e);
    disableStt();
};


// 볼륨 모니터링을 시작하는 함수
const startVolumeMonitoring = () => {
  if (navigator.mediaDevices) {
      navigator.mediaDevices.getUserMedia({ audio: true }).then((stream) => {
          if (!audioContext) {
              audioContext = new AudioContext();
              analyser = audioContext.createAnalyser();
              microphone = audioContext.createMediaStreamSource(stream);
              javascriptNode = audioContext.createScriptProcessor(2048, 1, 1);

              microphone.connect(analyser);
              analyser.connect(javascriptNode);
              javascriptNode.connect(audioContext.destination);

              javascriptNode.onaudioprocess = () => {
                if (analyser) {  
                const array = new Uint8Array(analyser.frequencyBinCount);
                  analyser.getByteFrequencyData(array);
                  let values = 0;
                  let length = array.length;
                  for (let i = 0; i < length; i++) {
                      values += (array[i]);
                  }
                  let average = values / length;
                  volume.value = Math.round(average); // 볼륨 상태 업데이트
                }
              };
          }
      }).catch((error) => {
          console.error("Error accessing microphone", error);
      });
  } else {
      alert("귀하의 브라우저는 오디오 모니터링을 지원하지 않습니다.");
  }
};

// 볼륨 모니터링을 중지하는 함수
const stopVolumeMonitoring = () => {
  if (javascriptNode) {
      javascriptNode.onaudioprocess = null;
      javascriptNode.disconnect();
      analyser.disconnect();
      microphone.disconnect();
      audioContext.close();
      audioContext = null;
      analyser = null;
      microphone = null;
      javascriptNode = null;
  }
};

onMounted(() => {
});

onUnmounted(() => {
  stopVolumeMonitoring();
});

</script>

<style lang="scss" scoped>
@import '@/assets/scss/layout/_gamebackground.scss'
</style>