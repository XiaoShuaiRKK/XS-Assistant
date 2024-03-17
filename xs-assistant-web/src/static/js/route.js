import { createRouter,createWebHashHistory } from "vue-router"
import MainForm from "@/components/MainForm.vue";
import LoginForm from "@/components/LoginForm.vue";
import Welcome from "@/components/Welcome.vue";

const routes = [
    {
        path: '/',
        redirect: '/home'
    },
    {
        path: '/home',
        name: 'WelcomeForm',
        component: Welcome,
        props: true
    },
    {
        path: '/login',
        name: 'LoginForm',
        component: LoginForm,
        props: true
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router