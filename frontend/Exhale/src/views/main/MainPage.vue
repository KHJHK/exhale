<template>
  <div :style="{ fontSize: fontSize + 'px' }">
    <Headers />
    <div class="sub-nav1">
        <div id="breadcrum">
          <RouterLink class="breadlink" :to="{name: 'MainPage'}">메인 홈</RouterLink>
          >
          <RouterLink class="breadlink" :to="{name: 'MainPage'}">언어재활코스</RouterLink>
        </div>
        <button class="enlarge" @click="enlarge">
        <img src="@/assets/plus.svg" class="plus">
        {{ msg }}
        </button> 
      </div>
    
    <p class="title">언어재활코스</p>

    <div class="mainbox">
      
      <div @click="toggleSubbox('box1')" class="box1">
        이름대기

        <div class="subbox1" :class="{ 'show': subboxStates.box1, 'hide': !subboxStates.box1 }">
          <div class="b1-1" style="font-size: 90%;" @click="navigateToReview1('행동')" >
            행동
          </div>
          <div class="b1-2" style="font-size: 90%;" @click="navigateToReview1('생물, 무생물')">
            생물<br>무생물
          </div>
          <div class="b1-3" style="font-size: 90%;" @click="navigateToReview1('장소')">
            장소
          </div>
        </div>

      </div>

      <div @click="toggleSubbox('box2')" class="box2">
        따라 말하기
        <div class="subbox2" :class="{ 'show': subboxStates.box2, 'hide': !subboxStates.box2 }">
          <div class="b2-1" style="font-size: 90%;" @click="navigateToReview2('2음절 연습')">
            2음절 연습
          </div>
          <div class="b2-2" style="font-size: 90%;" @click="navigateToReview2('3음절 연습')">
            3음절 연습
          </div>
        </div>

      </div>


      <div @click="toggleSubbox('box3')" class="box3">
        듣고 이해하기
        <div class="subbox3" :class="{ 'show': subboxStates.box3, 'hide': !subboxStates.box3 }">
          <div class="b3-1" style="font-size: 90%;" @click="navigateToReview3('그림 고르기')">
            그림<br>고르기
          </div>
          <div class="b3-2" style="font-size: 90%;" @click="navigateToReview3('텍스트 고르기')">
            텍스트<br>고르기
          </div>
        </div>
      </div>


      <div @click="toggleSubbox('box4')" class="box4">
        유창성
        <div class="subbox4" :class="{ 'show': subboxStates.box4, 'hide': !subboxStates.box4 }">
            <div class="b4-1" style="font-size: 90%;" @click="navigateToReview4()">
              대화하기
            </div>
        </div>
      </div>
    
    </div>

</div>
</template>

<script setup>
import { ref, onMounted, onUnmounted, watch, computed } from "vue";
import { useRouter } from "vue-router";
import Headers from "@/components/common/Headers.vue";
import { RouterView } from "vue-router";
import router from "@/router";

const fontSize = ref(16);
const msg = computed(() => fontSize.value > 21 ? '원래대로' : '글자확대');
const enlarge = () => {
  fontSize.value ++;
  if (fontSize.value > 22) {
    fontSize.value = 16
  };
};


// 클릭하면 subbox 나옴
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


// 위 name에 각 게임 페이지 링크 이름 넣어야 함
// 해당 게임 유형으로 이동
const navigateToReview1 = (category) => {
  router.push({ name: 'SayObject', params: { category } })
}
const navigateToReview2 = (category) => {
  router.push({ name: ' FollowUpSpeech', params: { category } })
}
const navigateToReview3 = (category) => {
  router.push({ name: 'ListeningComprehension', params: { category } })
}
const navigateToReview4 = () => {
  router.push({ name: ' Fluency' })
}

</script>

<style lang="scss" scoped>
@import "@/assets/scss/pages/_mainpage.scss";

</style>