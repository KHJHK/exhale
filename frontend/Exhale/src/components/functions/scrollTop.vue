<template>
  <button @click="scrollToTop" class="scroll-to-top">↑</button>
</template>

<script setup>
function scrollToTop() {
  window.scrollTo({
    top: 0,
    behavior: 'smooth' // 부드러운 스크롤 효과
  });
}

</script>

<style scoped>
.scroll-to-top {
  position: fixed;
  bottom: 20px;
  right: 20px;
  background-color: #6C9F9C; /* 배경색 */
  color: white; /* 아이콘 색상 */
  border: none; /* 테두리 없앰 */
  padding: 10px; /* 패딩 */
  border-radius: 5px; /* 모서리 둥글게 */
  cursor: pointer; /* 마우스 오버시 커서 변경 */
}
</style>