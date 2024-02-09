<template>
      <v-dialog
        v-model="dialog"
        fullscreen
        :scrim="false"
        transition="dialog-bottom-transition"
      >
        <!-- <template v-slot:activator="{ props }">
          <v-btn
            color="primary"
            dark
            v-bind="props"
          >
            Open Dialog
          </v-btn>
        </template> -->
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
                <div v-if="!isExit">
                  <img v-if="isRight" src="../../assets/right.png"/>              
                  <img v-if="!isRight" src="../../assets/wrong.svg"/>
                </div>
                <div v-if="isExit">
                  <img src="../../assets/stop.svg"/>
                </div>
              </div>
              <div class="buttons">
                <div v-if="isExit">
                  <button @click="isClose = true;">종료하기</button>
                </div>
                <div v-if="!isExit">
                  <button v-if="isRight" @click="reviewTick=true;">저장하기</button>
                  <button v-if="!isRight" @click="againTick=true;">다시풀기</button>
                  <button @click="nextTick = true;  dialog=false; ">넘어가기</button>
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
          isExit: Boolean,
        });  
        const dialog = ref(props.dialog);
        const isRight = ref(props.isRight);
        const isExit = ref(props.isExit);
        const isPause = ref(props.isPause);
        const nextTick = ref(false);
        const reviewTick = ref(false);
        const againTick = ref(false);

        const isClose = ref(false);

        const emit = defineEmits(["update:nextTick", "update:reviewTick",  "update:againTick", "update:isClose"]);
  
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

