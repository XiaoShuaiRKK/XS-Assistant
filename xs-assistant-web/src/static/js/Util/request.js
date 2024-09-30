import axios from "axios";
import store from "@/static/js/Util/store.js";
import router from "@/static/js/route.js";

const service = axios.create({
    baseURL: "http://127.0.0.1:20577/xs_assistant"
})

service.interceptors.request.use(
    config => {
        if(store.getters.getToken){
            config.headers['token'] = window.sessionStorage.getItem("token")
        }
        return config
    },
    error => {
        console.log(error)
        return Promise.reject(error)
    }
)

axios.interceptors.response.use(response => {
    let res = response.data
    if(res.code === 200){
        return response
    }
    return Promise.reject(res.message)
},error => {
    console.log(error)
    if(error.response.data){
        error.message = error.response.data.message
    }
    if(error.response.status === 401){
        router.push("/").then(r => {})
    }
    return Promise.reject(error)
})

export default service