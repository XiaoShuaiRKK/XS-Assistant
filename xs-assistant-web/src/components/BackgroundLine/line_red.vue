<script setup>

</script>

<template>
  <svg width="778" height="512" viewBox="0 0 778 512" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path id="line" stroke-dasharray="0 1800" d="M25 406C39 469 599 588 728 311C857 34 444 496 132 25" stroke="#FF4A4A" stroke-width="50" stroke-linecap="round"/>
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