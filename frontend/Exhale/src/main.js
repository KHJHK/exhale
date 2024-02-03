import './assets/main.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router/index.js'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import dayjs from './plugins/dayjs';
import axios from 'axios'

const app = createApp(App)
const pinia = createPinia();

app.config.globalProperties.$axios = axios;
app.use(createPinia())
app.use(router)
app.use(dayjs);

app.mount('#app')
