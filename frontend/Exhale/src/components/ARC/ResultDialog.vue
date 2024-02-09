<template>
    <v-row justify="center">
      <v-dialog
        v-model="dialog"
        fullscreen
        :scrim="false"
        transition="dialog-bottom-transition"
      >
        <template v-slot:activator="{ props }">
          <!-- <v-btn
            color="primary"
            dark
            v-bind="props"
          >
            Open Dialog
          </v-btn> -->
        </template>
        <v-card class="custom-dialog-card">
          
            <v-btn
              icon
              dark
              @click="dialog = false"
            >
              <v-icon>mdi-close</v-icon>
            </v-btn>
            정답 표시
        </v-card>
      </v-dialog>
    </v-row>
  </template>
    <script setup>
        import { ref, defineProps } from 'vue';
        const props = defineProps({
          dialog: Boolean
        });  
        const dialog = ref(props.dialog);
        const notifications = ref(false);
        const sound = ref(true);
        const widgets = ref(false);
    </script>

  <style>
  .dialog-bottom-transition-enter-active,
  .dialog-bottom-transition-leave-active {
    transition: transform .2s ease-in-out;
  }

  .custom-dialog-card {
    background-color: rgba(0, 0, 255, 0.5) !important;
  }
  </style>

