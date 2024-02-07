<script setup>
import { ref } from 'vue';
import { storeToRefs } from "pinia";
import { useAuthStore } from "@/stores/auth";
import { getCourseList, getProblem  } from '@/api/course.js';
import STT from '@/components/ARC/STT.vue';
import TTS from '@/components/ARC/TTS.vue';


// const authStore = useAuthStore();
// const { JWTtoken } = storeToRefs(authStore);
// // const token = JWTtoken.value;
// const token ="Bearer eyJhbGciOiJIUzI1NiJ9.eyJsb2dpbl9pZCI6InNzYWZ5MTIiLCJtZW1iZXJfaWQiOjMsInJvbGUiOiJST0xFX1VTRVIiLCJpYXQiOjE3MDcyNjQ2NzIsImV4cCI6MTcwNzI2NjQ3Mn0.E0kf1R_x_jHCJ9KJ8MP4hQU084U8KAtGb2Pefs_bLCc"; 

// const saveProblem = async () => {
//   try {
//     const { data } = await getProblem(1, token);
//     console.log(data);
//   } catch (error) {
//     console.error(error); 
//   }
// };
// saveProblem();

// const getCourses = async () => {
//   try {
//     const { data } = await getCourseList(token);
//     console.log(data);
//   } catch (error) {
//     console.error(error); 
//   }
// };
// getCourses();

// STT
const contentField = ref("")

const handleContentFieldChange = (value) => {
  contentField.value = value;
};

// TTS
const ttsText = ref("안녕하세요");

</script>

<template>
    <div>
      <STT 
        @update:modelValue="handleContentFieldChange"
        />
      <TTS
        :tts-text="ttsText"
      />
    </div>
</template>


<style lang="scss" scoped>

</style>