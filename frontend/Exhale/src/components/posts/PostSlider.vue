<template>
<!-- <div id="carouselExample" class="carousel slide">
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
</div> -->

<v-sheet
    class="mx-auto"
    width="100%"
  >
    <v-slide-group
      v-model="model"
      class="v-slide-group"
      show-arrows
    >
      <v-slide-group-item
        v-for="n in 10"
        :key="n"
        v-slot="{ isSelected, toggle, selectedClass }"
      >
        
        <v-card
          color="#E0E0E0"
          class="v-card"
          :class="[selectedClass]"
          height="230"
          width="230"
          @click="toggle"
        >
          <div class="d-flex fill-height align-center justify-center">
            
            <div>
              <p class="card-text">title</p>
            </div>  

            <v-scale-transition>
              <v-icon
                v-if="isSelected"
                color="black"
                size="48"
                icon="mdi-close-circle-outline"
              ></v-icon>
            </v-scale-transition>
          </div>
        </v-card>
      </v-slide-group-item>
    </v-slide-group>
  </v-sheet>


</template>

<script setup>
import { ref, onMounted } from 'vue'
import { boardList } from '@/api/boards';

const model = ref(null)

onMounted( async () => {
    const response = await boardList(1, 0);
    console.log(response)
})

</script>

<style scoped>
  /* @import "@/styles/main.scss"; */
  .v-card {
    border-radius: 0px;
    position: relative;
  }
  .v-slide-group {
    background-color: #ffffff;
    width: 100vw;
  }
  .card-text {
    position: absolute;
    z-index: 5;
    top: 50%;
    left: 0;
    width: 100%;
    text-align: center;
  }

  .v-slide-group__next i {
    display: none;
  }
</style>