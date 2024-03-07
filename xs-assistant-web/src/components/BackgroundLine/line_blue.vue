<script setup>

</script>

<template>
  <svg width="748" height="819" viewBox="0 0 748 819" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path id="line" stroke-dasharray="0 3000" d="M723 418C723 418 188.78 821.962 65 688C8.23899 626.57 16.4173 476.082 65 408C212 202 755.631 543.099 622 702C553.472 783.486 471.444 795.399 365 793C65.1035 786.242 379 25 379 25" stroke="#5D3AEA" stroke-width="50" stroke-linecap="round"/>
  </svg>
</template>

<style scoped>
@keyframes write {
  50%{
    stroke-dasharray: 3000 3000;
    stroke-dashoffset: 0;
  }
  100%{
    stroke-dashoffset: -3000;
  }
}
#line{
  animation: write 10s linear infinite;
}
</style>