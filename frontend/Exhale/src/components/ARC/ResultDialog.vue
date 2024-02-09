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
          
            <v-btn
              icon
              dark
              @click="dialog = false"
            >
              <v-icon>mdi-close</v-icon>
            </v-btn>
            <div class="content">
              <div v-if="isRight" >
                <img src="../../assets/right.png"/>
              </div>
              <div v-if="!isRight">
                <img src="../../assets/right.png"/>
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
        });  
        const dialog = ref(props.dialog);
        const isRight = ref(props.isRight);

        const emit = defineEmits(["update:modelValue"]);
  
        watch(dialog, () => {
          emit('update:modelValue', dialog.value);
        });
    </script>

  <style>
  .dialog-bottom-transition-enter-active,
  .dialog-bottom-transition-leave-active {
    transition: transform .2s ease-in-out;
  }

  .custom-dialog-card {
    background-color: rgba(108, 159, 156, 0.9) !important;
  }
  .content{
    margin: auto;
  }
  </style>

