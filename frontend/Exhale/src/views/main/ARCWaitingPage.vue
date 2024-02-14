<template>
    <div>
        <button @click="newWidow">이름대기</button>
        <button @click="openFluency">유창성</button>
        <button @click="openFollowUp">따라말하기</button>
        <button @click="openMatchingImage">듣기이해력(그림짝짓기)</button>
        <button @click="openMatchingText">듣기이해력(텍스트고르기)</button>
    </div>
</template>

<script setup>
// const getCourses = async () => {
//   try {
//     const { data } = await getCourseList(token);
//     console.log(data);
//   } catch (error) {
//     console.error(error); 
//   }
// };
// getCourses();

const newWidow = () => {
    window.open('http://localhost:5173/say-object');
}
const openFluency = () => {
    window.open('http://localhost:5173/fluency');
}
const openFollowUp = () => {
    window.open('http://localhost:5173/follow-up-speech');
}
const openMatchingImage = () => {
    window.open('http://localhost:5173/matching-image');
}
const openMatchingText = () => {
    window.open('http://localhost:5173/matching-text');
}
</script>

<style lang="scss" scoped>

</style>