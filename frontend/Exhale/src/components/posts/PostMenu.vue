<template>
  <div class="box">
    <div class="nav">
      <img src="@/assets/Nav.png" alt="hamburgerMenu" class="hamburgerMenu" >
    </div>
  </div>
</template>

<script>
export default {
  name: "Box",
};
</script>

<style>
.box {
  height: 28px;
  width: 204px;
}

.box .nav {
  height: 28px;
  right: 2vw;
  position: fixed;
  top: 3vh;
  width: 204px;
}
</style>