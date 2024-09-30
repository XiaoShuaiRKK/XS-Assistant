import './assets/main.css'
import { createApp } from 'vue'
import App from './App.vue'
import router from "@/static/js/route.js";
import axios from "axios";
import store from "@/static/js/Util/store.js";
import ElementPlus from 'element-plus'
import 'element-plus/theme-chalk/index.css'

axios.defaults.baseURL = "http://10.211.55.4:20577/xs_assistant"

const app = createApp(App);
app.provide('$axios',axios)
app.config.productionTip = false

router.beforeEach((to,from,next)=>{
    if(to.meta.requireAuth === true){
        if(store.state.token){
            next()
        }else{
            next({
                path: '/login',
                query: {redirect:to.fullPath}
            })
        }
    }else{  
        next()
    }
})

app.use(store).use(router).use(ElementPlus).mount('#app');

