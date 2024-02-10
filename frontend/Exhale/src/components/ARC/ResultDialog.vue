<template>
      <v-dialog
        v-model="dialog"
        fullscreen
        :scrim="false"
        transition="dialog-bottom-transition"
      >        
        <template v-slot:activator="{ props }">
          <v-btn @click="isExit=true; dialog=true;">연습 종료</v-btn>
          <v-btn @click="isPause=!isPause; dialog=true;">일시 정지</v-btn>
        </template>
   
        <v-card class="custom-dialog-card">
          
            <!-- <v-btn
              icon
              dark
              @click="dialog = false"
            >
              <v-icon>mdi-close</v-icon>
            </v-btn> -->
            <div class="content">
              <div class="images">
                <div v-if="isExit">
                  <img src="../../assets/stop.svg"/>
                </div>
                <div v-else-if="isPause">
                  <img src="../../assets/stop.svg"/>  <!-- todo 사진 변경 -->
                </div>
                <div v-else-if="dialog">
                  <img v-if="isRight" src="../../assets/right.png"/>              
                  <img v-if="!isRight" src="../../assets/wrong.svg"/>
                </div>
              </div>
              <div class="buttons">
                <div v-if="isExit">
                  <button @click="isClose = true; isExit=false; dialog=false;">종료하기</button>
                </div>
                <div v-else-if="isPause">
                  <button @click="isReturn=true; dialog=false;">돌아가기</button>
                </div>
                <div v-else-if="dialog">
                  <button v-if="isRight" @click="reviewTick=true;">저장하기</button>
                  <button v-if="!isRight" @click="againTick=true; dialog=false">다시풀기</button>
                  <button @click="nextTick = true;  dialog=false;">넘어가기</button>
                </div>
              </div>
            </div>
        </v-card>
      </v-dialog>
  </template>
    <script setup>
        import { ref, defineProps ,defineEmits, watch} from 'vue';
        const props = defineProps({
          dialog: Boolean,
          isRight: Boolean,
          isPause: Boolean,
          isReturn: Boolean,
        });  
        const dialog = ref(props.dialog);
        const isRight = ref(props.isRight);
        const isPause = ref(props.isPause);
        const isReturn = ref(props.isReturn);
        const nextTick = ref(false);
        const reviewTick = ref(false);
        const againTick = ref(false);

        const isExit = ref(false);
        const isClose = ref(false);

        const emit = defineEmits([
          "update:dialog",
          "update:nextTick", 
          "update:reviewTick",  
          "update:againTick", 
          "update:isClose", 
          "update:isPause",
          "update:isReturn",
        ]);
        watch(dialog, () => {
          emit('update:dialog', dialog.value);
        });  
        watch(nextTick, () => {
          emit('update:nextTick', nextTick.value);
        });
        watch(reviewTick, () => {
          emit('update:reviewTick', reviewTick.value);
        });
        watch(againTick, () => {
          emit('update:againTick', againTick.value);
        });        
        watch(isClose, () => {
          emit('update:isClose', isClose.value);
        });
        watch(isPause, () => {
          emit('update:isPause', isPause.value);
        });
        watch(isReturn, () => {
          emit('update:isReturn', isReturn.value);
        });

        watch(props, () => {
            dialog.value = props.dialog;
            isRight.value = props.isRight;
            isPause.value = props.isPause;
            isReturn.value = props.isReturn;
        });
    </script>

  <style lang="scss" scoped>
  .dialog-bottom-transition-enter-active,
  .dialog-bottom-transition-leave-active {
    transition: transform .2s ease-in-out;
  }

  .custom-dialog-card {
    background-color: rgba(108, 159, 156, 0.9) !important;
  }
  .content{
    width: 500px;
    margin: auto;
    .images {
      display: flex;
      justify-content: center;
      margin-bottom: 50px;
    }
  }
  .buttons{
    display: flex;
    justify-content: space-around;
    > div > button{
      padding: 2px 20px;
      border: 5px solid rgba(255, 255, 255, 0.62);
      background: rgba(255, 255, 255,0);
      border-radius: 90px;
      color: rgba(255, 255, 255);
      font-weight: 900;
    }

  }
  </style>

