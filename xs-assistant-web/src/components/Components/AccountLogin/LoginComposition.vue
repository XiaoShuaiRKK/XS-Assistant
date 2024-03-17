<script setup>

</script>

<template>
<div class="composition-login-box">
  <div class="composition-login-ele email">
    <p>电子邮箱</p>
    <input v-model="loginParams.email" type="text"
           class="login-input" placeholder="Your Email"/>
  </div>
  <div class="composition-login-ele password">
    <p>密码</p>
    <input v-model="loginParams.password" type="text"
           class="login-input" placeholder="Your Password"/>
  </div>
  <div class="composition-login-ele button">
    <div class="login-button login" @click="login()">
      <span>
        现在登录
      </span>
    </div>
    <div class="login-button register">
      <span>
        还没有账户？注册
      </span>
    </div>
  </div>
</div>
</template>

<style scoped>
.composition-login-box{
  margin-top: 5%;
}
.composition-login-ele{
  margin: 10% 0;
}
.composition-login-ele p{
  font-size: 26px;
  color: #ffffff;
  font-weight: bolder;
  margin-left: 5%;
}
.login-input{
  outline: none;
  font-size: 36px;
  border-radius: 36px;
  padding: 2% 5%;
}
.button{
  display: flex;
  justify-content: space-between;
  margin-top: 25%;
  .login-button{
    padding: 2% 5%;
    background: #ffffff;
    border: none;
    border-radius: 30px;
    span{
      font-weight: bolder;
      font-size: 26px;
      color: #000000;
    }
  }
}
.register{
  background: #000000 !important;
  border: #ffffff 1px solid !important;
  span{
    color: #ffffff !important;
  }
}
.login:hover{
  background: #e5e5e5 !important;
}
.register:hover{
  background: #383838 !important;
}
</style>

<script>
import { accountLogin } from "@/static/js/Api/LoginApi.js";
import router from "@/static/js/route.js";
export default {
  name: 'Login',
  data(){
    return{
      loginParams:{
        email: '',
        password: ''
      },
      responseResult: []
    }
  },
  methods:{
    login(){
      let _this = this;
      accountLogin({
        nameOrEmail: this.loginParams.email,
        password: this.loginParams.password
      }).then(res => {
        let code = res.data.status
        console.log(code)
        console.log(res.data)
        if(code == 200){
          console.log("ok")
          let data = res.data.data
          let token = data.token
          let user = data.customer
          _this.$store.commit('SET_TOKEN',token)
          _this.$store.commit('SET_USER_ID_NUMBER',user.idNumber)
          _this.$store.commit('SET_USER_INFO',user)
          router.push({
            name: 'WelcomeForm'
          })
        }else{
          alert(res.data.message)
        }
      })
    }
  }
}
</script>