<script setup>

</script>

<template>
  <svg width="503" height="681" viewBox="0 0 503 681" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path id="line" stroke-dasharray="0 1800" d="M478 546C478 546 -81.7323 733.988 44 617C174.358 495.708 488.399 26.0913 89 25" stroke="#FFF500" stroke-width="50" stroke-linecap="round"/>
  </svg>
</template>

<style scoped>
@keyframes write {
  50%{
    stroke-dasharray: 1800 1800;
    stroke-dashoffset: 0;
  }
  100%{
    stroke-dashoffset: -1800;
  }
}
#line{
  animation: write 10s linear infinite;
}
</style>