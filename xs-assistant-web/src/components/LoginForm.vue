<script setup>
import '../assets/Form/LoginFormStyle.css'
import LoginComposition from "@/components/Components/AccountLogin/LoginComposition.vue"
</script>

<template>
<div class="login-box">
  <div class="login-line-box">
    <div class="login-line"/>
    <div class="login-line two"/>
    <div class="login-line"/>
  </div>
  <div class="login-left">
    <div class="login-icon-box">
      <img src="../assets/Img/Logo/XS-LOGO.png" alt=""/>
    </div>
  </div>
  <div class="login-right">
    <div class="login-right-box">
      <div class="login-right-title">
        <p>
          现在加入
        </p>
      </div>
      <div class="login-right-container">
        <LoginComposition/>
      </div>
    </div>
  </div>
  <div>

  </div>
</div>
</template>

<script>

</script>

<style scoped>

</style>