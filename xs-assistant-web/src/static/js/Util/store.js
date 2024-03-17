import Vuex from 'vuex'

//sessionStorage 关闭服务器的时候会被清除
export default new Vuex.Store({
    state:{
        token: sessionStorage.getItem("token"),
        id:sessionStorage.getItem("idNumber"),
        user:JSON.parse(sessionStorage.getItem("user"))
    },
    mutations:{
        SET_TOKEN:(state,token)=>{
            state.token = token
            sessionStorage.setItem("token",token)
        },
        SET_USER_ID_NUMBER:(state,id)=>{
            state.id = id
            sessionStorage.setItem("id",id)
        },
        SET_USER_INFO:(state,user)=>{
            state.user = user
            sessionStorage.setItem("user",JSON.stringify(user))
        },
        REMOVE_INFO:(state)=>{
            state.token = ''
            state.user = {}
            sessionStorage.setItem("token",'')
            sessionStorage.setItem("user",JSON.stringify(''))
        }
    },
    getters:{
        getToken(state){
            return state.token
        },
        getUser(state){
            return state.user
        },
        getIsLogin(state){
            return state.token !== null && state.user !== null
        }
    },
    actions:{

    },
    modules:{

    }
})