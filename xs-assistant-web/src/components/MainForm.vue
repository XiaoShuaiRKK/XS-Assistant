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
          <div class="nav-header-btn_inner" id="nav-header-btn-line-box" @click="lineAnimal">
            <div/>
            <div/>
          </div>
        </div>
        <div class="nav-header-share">
          <a v-if="this.userInfo.idNumber === ''" @click="$router.push('login/login')" class="link-line">
            <p>
              Join us
            </p>
          </a>
          <a id="main-account-info" v-if="this.userInfo.idNumber !== ''">
            <p>{{userInfo.firstName}}</p>
            <br/>
            <p>{{userInfo.lastName}}</p>
          </a>
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
          <div class="main-container">

          </div>
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
      }
    }
  },
  methods: {
    lineAnimal(){
      document.getElementById('nav-header-btn-line-box').classList.toggle('animate')
    }
  }
}
</script>

<style scoped>

</style>
