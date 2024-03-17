<script setup>
import '../assets/Form/MainFormStyle.css'
import '@/static/js/Form/MainFormScript.js'

import BackgroundComponent from "@/components/Components/BackgroundComponent.vue";
import ProductCardWidget from "@/components/Components/Widget/ProductCardWidget.vue";
</script>

<template>
  <div class="main-all-box">
    <el-container direction="vertical" class="main-all-container">
      <el-aside width="20%" id="main-aside">
        <div class="nav-icon">
          <img src="../assets/Img/Logo/XS-LOGO.png" alt="">
        </div>
        <div class="nav-header-btn">
          <div class="nav-header-btn_inner">
            <div/>
            <div/>
          </div>
        </div>
        <div class="nav-header-share">
          <a v-if="userInfo.idNumber === ''" @click="$router.push('login')" class="link-line">
            <p>
              Join us
            </p>
          </a>
          <a id="main-account-info" v-if="userInfo.idNumber !== ''">
            <span>{{userInfo.firstName}}</span>
            <span>{{userInfo.lastName}}</span>
          </a>
        </div>
      </el-aside>
      <el-container class="main-main-box">
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
          <div class="main-container">

          </div>
        </div>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import store from "@/static/js/Util/store.js";

export default {
  setup(){
    this.userInfo = store.getters.getUser
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
      }
    }
  }
}
</script>

<style scoped>

</style>
