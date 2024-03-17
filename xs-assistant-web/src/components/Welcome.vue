<script setup>
import XIcon from './icons/icon_x.vue'
import SIcon from './icons/icon_s.vue'
import MainForm from "@/components/MainForm.vue";
</script>

<template>
  <div class="all">
    <div class="icon-box">
      <XIcon/>
      <SIcon/>
    </div>
    <div class="form-box">
      <MainForm id="main-form"/>
    </div>
  </div>
</template>
<style>
.all{
  width: 100%;
}
.icon-box{
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
  width: 100%;
  display: flex;
  justify-content: space-around;
  animation: ferry 3s linear forwards;
}
.form-box{
  width: 100%;
  height: 100%;
  animation: main-ferry 4s linear forwards;
}
#main-form{
  width: 100%;
  height: 100%;
}
@keyframes ferry {
  80%{
    opacity: 1;
    display: flex;
  }
  100%{
    opacity: 0;
    display: none;
  }
}
@keyframes main-ferry {
  0%{
    opacity: 0;
    display: none;
  }
  80%{
    opacity: 0;
    display: none;
  }
  100%{
    opacity: 1;
    display: block;
  }
}
</style>

<script>

</script>