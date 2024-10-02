<script>
import { register,registerSendCode } from "@/static/js/Api/registerApi.js"
import { ElMessage } from "element-plus";
export default{
    data(){
        return {
            isGetCode: false,
            codeText: '获取验证码',
            registerInfo: {
                firstName: null,
                lastName: null,
                email: null,
                password: null,
                code: null
            },
            checkPassword: null,
            checkErrorText: null,
            codeGetText: '获取验证码',
            countdown: 120,
            timer: null,
            registerLoading: false,
            isSendCodeLoading: false
        }
    },
    methods: {
        async registerUser(){
            this.registerLoading = true
            await register(this.registerInfo).then(response => {
                if(response.data === 200){
                    ElMessage.success("注册成功")
                }else{
                    ElMessage.error("注册失败,请稍后重试")
                    console.log(response.message)
                }
            }).catch(error => {
                ElMessage.error("注册失败,请稍后重试")
                console.log(error)
            })
            this.registerLoading = false
        },
        async sendCode(){
            this.isSendCodeLoading = true
            await registerSendCode({
                email: this.registerInfo.email
            }).then(response => {
                if(response.code === 200){
                    ElMessage.success("获取成功")
                    this.startCountdown()
                }else{
                    ElMessage.error("获取失败,请稍后重试")
                    console.log(response.message)
                }
            }).catch(error => {
                ElMessage.error("获取失败,请稍后重试")
                console.log(error)
            })
            this.isSendCodeLoading = false
        },
        handleCheckPassword(){
            if(this.registerInfo.password !== this.checkPassword){
                this.checkErrorText = "密码和确认密码不一致"
            }else{
                this.checkErrorText = null
            }
        },
        startCountdown(){
            this.isGetCode = true
            this.countdown = 120
            this.timer = setInterval(() => {
                if(this.countdown > 0){
                    this.countdown--
                }else{
                    clearInterval(this.timer)
                    this.isGetCode = false
                }
            },1000)
        }
    }
}
</script>

<template>
<div class="composition-register-box">
    <div class="login-out-box">
        <div>
            <el-text class="register-box-title register-name-text">First Name</el-text>
            <el-text class="register-box-title register-name-text">Last Name</el-text>
        </div>
        <el-input 
            placeholder="Please input first name"
            class="register-box register-name"/>
        <el-input 
            placeholder="Please input last name"
            class="register-box register-name"/>
    </div>
    <div class="login-out-box">
        <el-text class="register-box-title">Email</el-text>
        <el-input 
        placeholder="Please input Email"
        class="register-box login-email"/>
    </div>
    <div class="login-out-box">
        <el-text class="register-box-title">Password</el-text>
        <el-input
            v-model="this.registerInfo.password"
            placeholder="Please input password"
            show-password
            @input="handleCheckPassword"
            class="register-box login-password"/>
    </div>
    <div class="login-out-box">
        <div>
            <el-text class="register-box-title">Check Password</el-text>
        </div>
        <el-text type="danger">{{ this.checkErrorText }}</el-text>
        <el-input
            v-model="checkPassword"
            placeholder="Please input check password"
            show-password
            @input="handleCheckPassword"
            class="register-box register-check-password"/>
    </div>
    <div class="login-out-box">
        <div>
            <el-text class="register-box-title">Code</el-text>
        </div>
        <el-input 
        placeholder="Please input code"
        class="register-box register-code"/>
        <el-button
            type="primary"
            class="register-code-btn"
            :disabled="this.isGetCode"
            :loading="isSendCodeLoading"
            @click="sendCode">
            {{ this.codeText }}
        </el-button>
    </div>
    <div class="login-out-box login-button-box">
        <el-button type="primary"
            class="login-button"
            :loading="registerLoading"
            @click="registerUser"
            round>
            注册
        </el-button>
        <el-button class="login-button"
            @click="$router.push('login')" 
            round>
        已有账号?现在登录
        </el-button>
    </div>
</div>
</template>

<style scoped>
.composition-register-box{
  margin-top: 5%;
}
.register-box{
  width: 100%;
  font-size: large;
  padding: 10px 0;
}
.register-box-title{
  font-size: 26px;
  color: #fff;
}
.login-out-box{
  width: 100%;
  margin: 5px auto;
}
.login-button-box{
  margin-top: 20px;
}
.login-button{
  font-size: 26px;
  padding: 20px 30px !important;
}
.register-code{
    width: 60% !important;
    margin-right: 10px;
}
.register-code-btn{
    width: 35%;
    font-size: 18px;
}
.register-name{
    width: 45% !important;
    margin: 0 20px 0 0;
}
.register-name-text{
    margin-right: 50px;
}
</style>