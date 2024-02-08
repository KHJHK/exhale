<template>
  <div :style="{ fontSize: fontSize + 'px' }">
  <header>
    <Headers />
    <section class="sub-nav1">
        <div id="breadcrum">
          <RouterLink class="breadlink" :to="{name: 'MainPage'}">메인 홈</RouterLink>
          >
          <RouterLink class="breadlink" :to="{name: 'PostWholeListView'}">커뮤니티</RouterLink>
          >
          <RouterLink class="breadlink" :to="{name: 'PostWholeListView'}">전체</RouterLink>
        </div>
        <button class="enlarge" @click="enlarge" style="position: fixed; right: 0px; z-index: 10;">
        <img src="@/assets/plus.svg" class="plus">
        {{ msg }}
        </button> 
      </section>
  </header>
  <body>
    
    <p class="title">언어재활코스</p>

    <div class="mainbox">
      
      <div @click="toggleSubbox('box1')" class="box1">
        이름대기

        <div class="subbox1" :class="{ 'show': subboxStates.box1, 'hide': !subboxStates.box1 }">
          <div class="b1-1">
            행동
          </div>
          <div class="b1-2">
            생물
          </div>
          <div class="b1-3">
            무생물
          </div>
          <div class="b1-4">
            장소
          </div>
        </div>

      </div>

      <div @click="toggleSubbox('box2')" class="box2">
        따라 말하기
        <div class="subbox2" :class="{ 'show': subboxStates.box2, 'hide': !subboxStates.box2 }">
          <div class="b2-1">
            2음절 연습
          </div>
          <div class="b2-2">
            3음절 연습
          </div>
        </div>

      </div>


      <div @click="toggleSubbox('box3')" class="box3">
        듣고 이해하기
        <div class="subbox3" :class="{ 'show': subboxStates.box3, 'hide': !subboxStates.box3 }">
          <div class="b3-1">
            그림 고르기
          </div>
          <div class="b3-2">
            텍스트 고르기
          </div>
        </div>
      </div>


      <div @click="toggleSubbox('box4')" class="box4">
        유창성
        <div class="subbox4" :class="{ 'show': subboxStates.box4, 'hide': !subboxStates.box4 }">
            <div class="b4-1">
              대화하기
            </div>
        </div>
      </div>
    
    </div>

  </body>
  <footer>
    <Footers/>
  </footer>

</div>
</template>

<script setup>
import { ref, onMounted, onUnmounted, watch, computed } from "vue";
import { useRouter } from "vue-router";
import Headers from "@/components/common/Headers.vue";
import Footers from "@/components/common/Footers.vue";
import { RouterView } from "vue-router";

const fontSize = ref(16);
const msg = computed(() => fontSize.value > 21 ? '원래대로' : '글자확대');
const enlarge = () => {
  fontSize.value ++;
  if (fontSize.value > 22) {
    fontSize.value = 16
  };
};


// 클릭하면 subbox 나오는 함수
const showSubbox = ref(false);
const animationDirection = ref('');

const subboxStates = ref({
  box1: false,
  box2: false,
  box3: false,
  box4: false,
})

const toggleSubbox = (boxKey) => {
  subboxStates.value[boxKey] = !subboxStates.value[boxKey]
  Object.keys(subboxStates.value).forEach(key => {
    if (key !== boxKey) subboxStates.value[key] = false
  })
}

// export default {
//   mounted() {
//     // URL에서 인가 코드 추출
//     const queryParams = new URLSearchParams(window.location.search);
//     const code = queryParams.get('code');
    
//     if (code) {
//       // 인가 코드를 로컬 스토리지에 저장
//       localStorage.setItem('kakao_auth_code', code);

//       // 추가적인 처리가 필요할 수 있음
//     }
//   }
// }
// const value = localStorage.getItem('kakao_auth_code')

</script>

<style lang="scss" scoped>
@import "@/assets/scss/pages/_mainpage.scss";

</style>