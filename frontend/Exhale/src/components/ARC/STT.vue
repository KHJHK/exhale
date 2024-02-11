<template>
  <div class="speak">
    <div class="speakBtn">
      <button @click="enableStt" v-show="!sttRunning">
        <img src="@/assets/mic.svg" class="mic" >
      </button>
      <button @click="disableStt" v-show="sttRunning">
        <img src="@/assets/mic.svg" class="mic" >
      </button>
      <SoundWave :volume="volume" class="soundwave" />
      <div class="message">{{ message }}</div>
      <div class="volume">Volume: {{ volume }}</div>
    </div>
    <form @submit.prevent="onSubmit" ref="sttForm">
      <textarea class="textarea" name="sttText" v-model="sttText"></textarea>
    </form>
  </div>
</template>

<script setup>
import { ref, watch, defineEmits, onMounted, onUnmounted } from 'vue';
import SoundWave from './SoundWave.vue';

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
    sttTextValue = sttText.value;
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
          if (audioContext === null) {
              audioContext = new AudioContext();
              analyser = audioContext.createAnalyser();
              microphone = audioContext.createMediaStreamSource(stream);
              javascriptNode = audioContext.createScriptProcessor(2048, 1, 1);

              microphone.connect(analyser);
              analyser.connect(javascriptNode);
              javascriptNode.connect(audioContext.destination);

              javascriptNode.onaudioprocess = () => {
                  const array = new Uint8Array(analyser.frequencyBinCount);
                  analyser.getByteFrequencyData(array);
                  let values = 0;
                  let length = array.length;
                  for (let i = 0; i < length; i++) {
                      values += (array[i]);
                  }
                  let average = values / length;
                  volume.value = Math.round(average); // 볼륨 상태 업데이트
              };
          }
      }).catch((error) => {
          console.error("Error accessing microphone", error);
      });
  } else {
      alert("Your browser does not support audio monitoring");
  }
};

// 볼륨 모니터링을 중지하는 함수
const stopVolumeMonitoring = () => {
  if (audioContext && microphone && javascriptNode) {
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

.volume {
  position: fixed;
  top: 90%;
  width: 70px;
}

.soundwave {
  position: fixed;
  top: 79%;
  left: 60%;
  transform: translate(-50%, -50%);
  width: 30%;
  max-width: 1000px;
  z-index: 50;
  color: rgb(108, 159, 156);

}
</style>