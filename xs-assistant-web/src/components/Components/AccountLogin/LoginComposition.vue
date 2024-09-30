<script setup>

</script>

<template>
<div class="composition-login-box">
  <div class="login-out-box">
    <el-text class="login-box-title">Email</el-text>
    <el-input v-model="this.loginParams.email" 
      placeholder="Please input Email"
      class="login-box login-email"/>
  </div>
  <div class="login-out-box">
    <el-text class="login-box-title">Password</el-text>
    <el-input v-model="this.loginParams.password" 
      placeholder="Please input password"
      show-password
      class="login-box login-email"/>
  </div>
  <div class="login-out-box login-button-box">
    <el-button type="primary"
      @click="login"
      class="login-button" round>
      登录
    </el-button>
    <el-button class="login-button" round>
      没有账号?现在注册
    </el-button>
  </div>
</div>
</template>

<style scoped>
.composition-login-box{
  margin-top: 5%;
}
.login-box{
  width: 100%;
  font-size: large;
  padding: 20px 0;
}
.login-box-title{
  font-size: 26px;
  color: #fff;
}
.login-out-box{
  width: 100%;
  margin: 20px auto;
}
.login-button-box{
  margin-top: 30px;
}
.login-button{
  font-size: 26px;
  padding: 20px 30px !important;
}
</style>

<script>
import { accountLogin } from "@/static/js/Api/LoginApi.js";
import router from "@/static/js/route.js";
import { ElMessage } from "element-plus";
import store from "@/static/js/Util/store.js";
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
        console.log("Login")
        let code = res.data.status
        console.log(code)
        console.log(res.data)
        if(code == 200){
          ElMessage.success('登录成功')
          let data = res.data.data
          let token = data.token
          let user = data.customer
          _this.$store.commit('SET_TOKEN',token)
          _this.$store.commit('SET_USER_ID_NUMBER',user.idNumber)
          _this.$store.commit('SET_USER_INFO',user)
          console.log(user.idNumber)
          router.push({
            name: 'WelcomeForm'
          })
        }else{
          ElMessage.warning(res.data.message)
        }
      }).catch(error => {
        ElMessage.warning(error.message)
      })
    }
  }
}
</script>