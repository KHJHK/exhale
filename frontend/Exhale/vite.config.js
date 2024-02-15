import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
 
// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  // server: {
  //   proxy: {
  //     "/api": {
  //       target: "http://i10b208.p.ssafy.io/api",
  //       // rewrite: (path) => path.replace(/^\/api/, ""),
  //       changeOrigin: true,
  //     },
  //   },
  // },
  
  // css : {
  //   preprocessorOptions: {
  //     scss: {
  //       additionalData: `
  //       @import "./src/assets/scss/abstracts/_variables";
  //       @import "./src/assets/scss/abstracts/_mixins";
  //       `
  //     }
  //   }
  // }
})