<template>
  <div id="carouselExample" class="carousel slide">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="@/assets/dog1.jpg" alt="...">
      <img src="@/assets/dog3.jpg" alt="...">
      <img src="@/assets/dog1.jpg" alt="...">
    </div>
    <div class="carousel-item">
      <img src="@/assets/dog2.jpg" alt="...">
      <img src="@/assets/dog2.jpg" alt="...">
      <img src="@/assets/dog2.jpg" class="d-block w-50" alt="...">
    </div>
    <div class="carousel-item">
      <img src="@/assets/dog3.jpg" alt="...">
      <img src="@/assets/dog3.jpg" alt="...">
    </div>
  </div>
  
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
</template>

<script>
</script>

<style lang="scss" scoped>
  @import "@/assets/scss/components/_carousel.scss";
</style>