import { createRouter,createWebHashHistory } from "vue-router"
import MainForm from "@/components/MainForm.vue";
import LoginForm from "@/components/LoginForm.vue";
import Welcome from "@/components/Welcome.vue";
import LoginComponent from "@/components/Components/AccountLogin/LoginComposition.vue"
import RegisterComponent from "@/components/Components/AccountLogin/RegisterComposition.vue"

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
        props: true,
        children: [
            {
                path: 'login',
                name: 'LoginComponent',
                component: LoginComponent
            },
            {
                path: 'register',
                name: 'RegisterComponent',
                component: RegisterComponent
            }
        ]
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router