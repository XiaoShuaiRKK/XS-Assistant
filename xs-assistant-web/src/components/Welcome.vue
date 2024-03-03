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
      <MainForm/>
    </div>
  </div>
</template>
<style scoped>
.all{
  width: 100%;
}
.icon-box{
  width: 80vw;
  display: flex;
  justify-content: space-around;
  animation: ferry 3s linear forwards;
}
.form-box{
  width: 100%;
  animation: main-ferry 4s linear forwards;
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