<template>
    <div class="nav scale-up-hor-right">
      <img src="@/assets/Nav.png" alt="hamburgerMenu" class="hamburgerMenu" >
    </div>
</template>

<script>

</script>

<style scoped>

.nav {
  display: inline;
  width: 100px;
  height: 28px;
}

.hamburgerMenu {
  margin-top: 10px;
  margin-right: 10px;
}

.scale-up-hor-right {
	animation: scale-up-hor-right 2s cubic-bezier(0.390, 0.575, 0.565, 1.000) both;
}

 @keyframes scale-up-hor-right {
  0% {
    transform: scaleX(0.1);
    transform-origin: 100% 100%;
  }
  100% {
    transform: scaleX(1);
    transform-origin: 100% 100%;
  }
}

</style>