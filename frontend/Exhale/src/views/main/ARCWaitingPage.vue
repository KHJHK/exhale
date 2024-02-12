<template>
    <div>
        <button @click="newWidow">이름대기</button>
        <button @click="openFluency">유창성</button>
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
</script>

<style lang="scss" scoped>

</style>