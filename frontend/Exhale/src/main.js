import '@/assets/scss/abstracts/_variables.scss'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router/index.js'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import dayjs from './plugins/dayjs';


const app = createApp(App)
const pinia = createPinia();

app.use(createPinia())
app.use(router)
app.use(dayjs);

app.mount('#app')
// app.config.globalProperties.axios = axios;
window.Kakao.init('6b4e32f2e798095358c3333761e1c31b');
// console.log(kakao.isinitialized());