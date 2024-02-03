<template>
  <div class="nav scale-up-hor-right">
    <img src="@/assets/Nav.png" alt="hamburgerMenu" class="hamburgerMenu">
    <div class="background  scale-up-hor-right"></div>
  </div>
</template>

<script>

</script>

<style scoped>

/* .background {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(128, 128, 128, 0.1);
  z-index: 1;
  animation: scale-up-hor-right 2s cubic-bezier(0.390, 0.575, 0.565, 1.000) both;
}

.hamburgerMenu {
  z-index: 2;
  position: relative;
}

.nav {
  position: relative;
  width: 100vw;
  height: 100vh;
}


.scale-up-hor-right {
animation: scale-up-hor-right 2s cubic-bezier(0.390, 0.575, 0.565, 1.000) both;
}

@keyframes scale-up-hor-right {
0% {
  transform: scaleX(0);
  transform-origin: 100% 100%;
}
100% {
  transform: scaleX(5.2);
  transform-origin: 100% 100%;
}
} */

.background {
  position: fixed;
  top: 0;
  right: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(228, 228, 228, 0.5);
  z-index: 1000;
  animation: scale-up-hor-right 1s cubic-bezier(0.390, 0.575, 0.565, 1.000) both;
}

.hamburgerMenu {
  position: fixed;
  top: 10px;
  right: 10px;
  z-index: 1001;
  animation: scale-up-hor-right2 2s cubic-bezier(0.390, 0.575, 0.565, 1.000) both;
}

.nav {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  z-index: 1000;
}

@keyframes scale-up-hor-right {
  0% {
    transform: translateX(100%);
  }
  100% {
    transform: translateX(0);
  }
}

@keyframes scale-up-hor-right2 {
0% {
  transform: scaleX(0);
  transform-origin: 100% 100%;
}
100% {
  transform: scaleX(5.2);
  transform-origin: 100% 100%;
}
}

</style>