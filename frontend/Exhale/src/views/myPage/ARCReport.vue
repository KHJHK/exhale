<template>
  <div id="page">
    <SimpleHdear />
    <div id="sidebar">
      <div id="profile-image"></div>
      <div id="nickname"></div>
    </div>
    <div id="main"></div>
    백에서 곧 통계자료가 들어오면 간드러지게 게시할게요
    <Footers />
  </div>
</template>

<script setup>
import SimpleHdear from "@/components/common/SimpleHeader.vue";
import Footers from "@/components/common/Footers.vue";
</script>

<style lang="scss" scoped>
#page {
  height: 100vh;
}

#sidebar {
  min-height: 80%;
  width: 30%;
  padding: 0% 7%;
  background-color: aqua;
  position: fixed;
  left: 0;
}

#main {
  min-height: 80%;
  width: 70%;
  background-color: aquamarine;
  margin-left: 30%;
}
</style>
