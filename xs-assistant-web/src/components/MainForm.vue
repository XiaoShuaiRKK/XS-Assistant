<script setup>
import '../assets/Form/MainFormStyle.css'

import '@/static/js/Form/MainFormScript.js'

import RedLine from './BackgroundLine/line_red.vue'
import YellowLine from './BackgroundLine/line_yellow.vue'
import GreenLine from './BackgroundLine/line_green.vue'
import BlueLine from './BackgroundLine/line_blue.vue'
</script>

<template>
  <div class="main-all-box">
    <el-container direction="vertical" class="main-all-container">
      <el-aside width="20%" id="main-aside">
        <div class="nav-icon">
          <img src="../assets/Logo/XS-LOGO.png" alt="">
        </div>
        <div class="nav-header-btn">
          <div class="nav-header-btn_inner">
            <div/>
            <div/>
          </div>
        </div>
        <div class="nav-header-share">
          <p>
            Join us
          </p>
        </div>
      </el-aside>
      <el-container class="main-main-box">
        <div class="main-background-box">
          <RedLine class="main-background red"/>
          <YellowLine class="main-background yellow"/>
          <GreenLine class="main-background green"/>
          <BlueLine class="main-background blue"/>
        </div>
        <div class="main-title">
          <p>XS-Assistant</p>
        </div>
        <div class="main-container">

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
        </div>
      </el-container>

    </el-container>
  </div>
</template>

<style scoped>

</style>
