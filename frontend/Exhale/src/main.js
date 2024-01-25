import './assets/main.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router/index.js'


const app = createApp(App)
const pinia = createPinia();

app.use(createPinia())
app.use(router)

app.mount('#app')
// app.config.globalProperties.axios = axios;
window.Kakao.init('6b4e32f2e798095358c3333761e1c31b');
// console.log(kakao.isinitialized());