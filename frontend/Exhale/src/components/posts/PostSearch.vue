<template>
    <!-- 모달 -->
    <div v-show="modalActive" id="search-modal-search">
      <div id="search-modal-search-box">
        <input 
        type="text" 
        @keyup="searchInput" 
        placeholder="SEARCH" 
        id="search-modal-search--input">
      </div>
      <!-- <div v-show="modalActive" @click="modalActiveTogle" id="search-modal"></div> -->
    </div>
    <!-- @keyup.esc="modalActiveTogle"  -->
    
    <!-- 모달 배경 -->
    <!-- <div v-if="modalActive" @click="modalActiveTogle" id="search-modal-bg"></div> -->

      <!-- 홈화면에 보이는 검색창 -->
      <!-- <span id="home-search">
        <span id="home-search-box"> -->
          <!-- 검색창은 비활성화 -->
          <!-- <input placeholder="SEARCH" type="text" id="search--input" readonly>
        </span>
      </span> -->
      
</template>

<script setup>
import { ref } from 'vue'

const modalActive = ref(false)
const ableSearch = ref(true)
const searchInputData = ref(null)

const modalActiveTogle = () => {
  modalActive.value = !modalActive.value
  setTimeout(() => {
    const searchModalInputTag = document.querySelector('#search-modal-search--input')
    searchModalInputTag.focus()
  }, 200)
}

const searchInput = () => {
  if (ableSearch.value) {
    ableSearch.value = false
    const searchModalInputTag = document.querySelector('#search-modal-search--input')
    setTimeout(() => {
      searchInputData.value = searchModalInputTag.value
      ableSearch.value = true
    }, 1000)
  }
}


</script>

<style scoped>
.modal {
  overflow-y: auto;
  max-height: 100%;
}

/* 홈화면에서의 서치 요소(작동 안하고, 모달을 띄우는데만 사용) */
#home-search {
  display: flex;
  justify-content: center;
  margin-bottom: 80px;
}

#home-search-box input {
  font-family: 'NanumSquareNeo-Variable';
  outline: none;
  border: none;
  border-bottom: white solid;
  background-color: #ffffff00;
  color: white;
}

#home-search-box input:hover {
  border-bottom: gray solid;
  transition: all 0.2s;
  transform: scale(1.03);
}

#home-search-box img {
  width: 35px;
  margin-right: 10px;
}

/* 서치용 모달 내부 요소 */
#search-modal {
  z-index: 10;
  position: fixed;
  top: 0px;
  width: 100vw;
  height: 100vw;
}

#search-modal-search {
  z-index: 20;
  width: 60%;
  /* position: fixed; */
  top: 330px;
  display: flex;
  align-items: center;
  flex-direction: column;
  flex-wrap: nowrap;
}

#search-modal-search-box {
  z-index: 30;
  padding: 0px 10px;
  border-bottom: 3px solid rgba(108, 159, 156, 0.8);
  /* margin-top: 50px; */
}

#search-modal-search-box input {
  font-family: 'NanumSquareNeo-Variable';
  outline: none;
  border: none;
  border-bottom: white solid;
  background-color: #ffffff00;
  color: white;
}

#search-modal-search-box input:hover {
  transition: all 0.2s;
  transform: scale(1.03);
}

#search-modal-search-box img {
  width: 35px;
  margin-right: 10px;
}

#search-modal-result {
  width: 100%;
  max-width: 900px;
  z-index: 30;
}

/* 배경 애니메이션 */
#search-modal-bg {
  z-index: 10;
  position: fixed;
  top: 0px;
  right: 0px;
  width: 100%;
  height: 100%;
  background-color: rgba(108, 159, 156, 0.9);
  /* backdrop-filter: blur(20px); */
}

button {
  color: rgba(116, 116, 116, 0);
}
/* Keyframes for fade-in animation */
@-webkit-keyframes fade-in {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}

@keyframes fade-in {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}
</style>