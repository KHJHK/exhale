// stores/auth.js
import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    isLoggedIn: false,
    userData: null
  }),
  actions: {
    setUser(data) {
      this.isLoggedIn = true;
      this.userData = data;
    },
    clearUser() {
      this.isLoggedIn = false;
      this.userData = null;
    }
  }
});
