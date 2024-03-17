<script setup>
window.onload = function () {
  dotAnimation();
}
function dotAnimation(){
  const circle = document.querySelector(".circle-box");
  window.onmousemove = function(ev) {
    let ofLeft = document.documentElement.offsetLeft || document.body.offsetLeft;
    let ofTop = document.documentElement.offsetTop || document.body.offsetTop;
    let oLeft = ev.clientX + ofLeft;
    let oTop = ev.clientY + ofTop + window.pageYOffset;
    circle.style.display = "block";
    circle.style.left = oLeft + "px";
    circle.style.top = oTop + "px";
  }
}
</script>

<template>
  <div class="circle-box"/>
</template>

<style scoped>
.circle-box {
  display: none;
  position: absolute;
  width: 50px;
  height: 50px;
  margin: -15px 0 0 -15px;
  background: rgb(255,255,255);
  -webkit-border-radius: 50%;
  -moz-border-radius: 50%;
  border-radius: 50%;
  opacity: 80%;
  z-index: 0;
}
</style>