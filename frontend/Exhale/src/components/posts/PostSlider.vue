<template>
  <div id="carouselExample" class="carousel slide">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="@/assets/dog1.jpg" class="w-25" alt="...">
      <img src="@/assets/dog1.jpg" class="w-25" alt="...">
      <img src="@/assets/dog1.jpg" class="d-block h-50" alt="...">
    </div>
    <div class="carousel-item">
      <img src="@/assets/dog2.jpg" class="w-50" alt="...">
      <img src="@/assets/dog2.jpg" class="w-50" alt="...">
      <!-- <img src="@/assets/dog2.jpg" class="d-block w-50" alt="..."> -->
    </div>
    <div class="carousel-item">
      <img src="@/assets/dog3.jpg" class="w-50" alt="...">
      <img src="@/assets/dog3.jpg" class="w-50" alt="...">
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
  export default {
    
  }
</script>

<style scoped>
.carousel-control-next {
  color: red;
}
</style>