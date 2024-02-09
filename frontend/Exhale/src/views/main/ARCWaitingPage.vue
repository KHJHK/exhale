<template>
    <div>
        <button @click="newWidow">게임 시작</button>
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

</script>

<style lang="scss" scoped>

</style>