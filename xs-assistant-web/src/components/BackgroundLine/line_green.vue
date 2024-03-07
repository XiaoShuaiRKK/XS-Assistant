<script setup>

</script>

<template>
  <svg width="752" height="596" viewBox="0 0 752 596" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path id="line" stroke-dasharray="0 1800" d="M377 25C246.556 285.304 518.1 739.522 692 506C762 412 730 237 565 251C400 265 25 540 25 540" stroke="#ADFF00" stroke-width="50" stroke-linecap="round"/>
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