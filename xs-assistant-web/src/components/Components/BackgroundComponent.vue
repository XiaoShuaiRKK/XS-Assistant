<script setup>

import RedLine from "@/components/BackgroundLine/line_red.vue";
import BlueLine from "@/components/BackgroundLine/line_blue.vue";
import YellowLine from "@/components/BackgroundLine/line_yellow.vue";
import GreenLine from "@/components/BackgroundLine/line_green.vue";
</script>

<template>
  <div class="main-background-box">
    <RedLine class="main-background red"/>
    <YellowLine class="main-background yellow"/>
    <GreenLine class="main-background green"/>
    <BlueLine class="main-background blue"/>
  </div>
</template>

<style scoped>
.main-background-box{
  position: fixed;
  width: 100%;
  height: 100%;
  z-index: -5;
}
.main-background{
  width: 100%;
  height: 100%;
  position: static;
  z-index: -2;
  left: -20em;
}
.red{
  width: 100%;
  height: 120%;
  top: -150px;
  position: absolute;
}
.yellow{
  width: 100%;
  height: 120%;
  top: -80px;
  left: 50vw;
  position: absolute;
}
.green{
  width: 100%;
  height: 120%;
  top: -50px;
  position: absolute;
}
.blue{
  width: 100%;
  height: 120%;
  top: -50px;
  left: 30vw;
  position: absolute;
}
</style>