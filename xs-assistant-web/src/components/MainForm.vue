<script setup>
import '../assets/Form/MainFormStyle.css'
import '@/static/js/Form/MainFormScript.js'

import BackgroundComponent from "@/components/Components/BackgroundComponent.vue";
import ProductCardWidget from "@/components/Components/Widget/ProductCardWidget.vue";
</script>

<template>
  <div class="main-all-box">
    <el-container direction="vertical" class="main-all-container">
      <el-aside width="20%" id="main-aside"
        class="main-aside"
        :class="{ 'aside-close' : !isAsideOpen, 'aside-open' : isAsideOpen }">
        <div class="nav-aside-info">
          <div class="nav-icon">
            <img src="../assets/Img/Logo/XS-LOGO.png" alt="">
          </div>
          <div class="nav-header-btn">
            <div class="nav-header-btn_inner" id="nav-header-btn-line-box" @click="lineAnimal">
              <div :class="{ 'aside-btn-one-close' : !isAsideOpen, 'aside-btn-one-open' : isAsideOpen }"
                class="aside-btn-one"/>
              <div :class="{ 'aside-btn-two-close' : !isAsideOpen, 'aside-btn-two-open' : isAsideOpen }"
                class="aside-btn-two"/>
            </div>
          </div>
          <div class="nav-header-share">
            <a v-if="this.userInfo.idNumber === ''" @click="$router.push('login/login')" class="link-line">
              <p>
                Join us
              </p>
            </a>
            <a id="main-account-info" v-if="this.userInfo.idNumber !== ''">
              <el-avatar class="user-icon-img">
                {{ userInfo.lastName }}
              </el-avatar>
            </a>
          </div>
        </div>
      </el-aside>
      <div class="main-main-box">
        <div class="circle-box"/>
        <BackgroundComponent/>
        <div class="main-title">
          <p>XS-Assistant</p>
        </div>
        <div class="main-container product">
          <div class="main-container-title">
            <span>P</span>
            <span>r</span>
            <span>o</span>
            <span>d</span>
            <span>u</span>
            <span>c</span>
            <span>t</span>
          </div>
          <div class="main-container card">
            <ProductCardWidget logo="../../assets/Img/icon/apple-fill-bk.png" title="XS-Assistant-IOS" class="main-container-widget-card"/>
            <ProductCardWidget class="main-container-widget-card"/>
            <ProductCardWidget class="main-container-widget-card"/>
          </div>
        </div>
        <div class="main-container empty">

        </div>
        <div class="main-container assistant">
          <el-row :gutter="14" style="height: 20%;">
            <el-col :span="10">
              <div class="main-container-card">

              </div>
            </el-col>
            <el-col :span="4">
              <div class="main-container-card-title">
                <p>
                  Assistant
                </p>
                <span>
                  一款能帮助，监督你的学习笔记助手
                </span>
              </div>
            </el-col>
          </el-row>
        </div>
      </div>
    </el-container>
  </div>
</template>

<script>
import store from "@/static/js/Util/store.js";

export default {
  setup(){
  },
  created(){
    if(store.getters.getUser !== null){
      this.userInfo = store.getters.getUser
      console.log(this.userInfo)  
    }
  },
  data(){
    return{
      userInfo:{
        id:0,
        firstName:'',
        lastName:'',
        email:'',
        birth:'',
        idNumber:'',
        areaId:0
      },
      isAsideOpen: false
    }
  },
  methods: {
    lineAnimal(){
      this.isAsideOpen = !this.isAsideOpen
    }
  }
}
</script>

<style scoped>
.main-aside{
  transition: width 0.3s ease;
  width: 10%;
}
.aside-close{
  width: 10%;
}
.aside-open{
  width: 80%;
}
.nav-aside-info{
  position: fixed;
  display: inline-flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  height: 100%;
  width: 10%;
  background: black;
  z-index: 10;
}
.nav-icon{
  margin: 0 auto;
  width: 50%;
}
.nav-icon img{
  width: 100%;
}
.nav-header-btn{
  width: 100%;
  height: 40%;
}
.nav-header-share{
  display: flex;
  justify-content: space-around;
  flex-direction: column;
}
.aside-btn-one{
  transform: rotateZ(0);
  transition: transform 0.3s ease;
}
.aside-btn-one-open{
  transform: rotateZ(60deg);
}
.aside-btn-two{
  transform: rotateZ(0);
  transition: transform 0.3s ease;
}
.aside-btn-two-open{
  transform: rotateZ(-60deg);
}
.user-icon-img{
  background-color: #ffc961;
  color: #446e00;
  font-weight: bold;
}
</style>
