import '@/assets/scss/main.scss'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router/index.js'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import dayjs from './plugins/dayjs';
<<<<<<< HEAD
import axios from 'axios'
=======
import { mdiAccount } from '@mdi/js'

>>>>>>> feature/S10P12B208-194_커뮤니티페이지_화면설계

const app = createApp(App)
const pinia = createPinia();

<<<<<<< HEAD
app.config.globalProperties.$axios = axios;
=======
// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
const vuetify = createVuetify({
  components,
  directives,
  icons: {
    defaultSet: 'mdi',
  }
})

>>>>>>> feature/S10P12B208-194_커뮤니티페이지_화면설계
app.use(createPinia())
app.use(router)
app.use(dayjs);
app.use(vuetify)
app.mount('#app')
<<<<<<< HEAD
=======


// app.config.globalProperties.axios = axios;
window.Kakao.init('6b4e32f2e798095358c3333761e1c31b');
// console.log(kakao.isinitialized());
>>>>>>> feature/S10P12B208-194_커뮤니티페이지_화면설계
