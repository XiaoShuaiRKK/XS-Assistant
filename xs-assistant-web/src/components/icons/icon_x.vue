<template>
  <svg width="290" height="392" viewBox="0 0 290 392" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path id="text" stroke-dasharray="0 1334" d="M5 9.99997C76 -48 167.377 509.434 244 362C278.781 295.075 304.397 203.417 262 130C202.591 27.1241 -15 305 19 313" stroke="#C3FE63" stroke-width="10" stroke-linecap="round"/>
  </svg>
</template>
<style>
@keyframes write {
  100%{
    stroke-dasharray: 1334 1334;
  }
}
#text{
  animation: write 2s linear forwards;
}
</style>