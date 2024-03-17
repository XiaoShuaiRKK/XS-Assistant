<script setup>
import '../../../assets/Widget/ProductCardStyle.css'
const params = defineProps({
  title:{
    type:String,
    default:'XS-Assistant-xxx'
  },
  describe:{
    type:String,
    default:'This the describe'
  },
  logo:{
    type:String,
    default:'../../assets/Img/icon/apple-fill-bk.png'
  }
})
let path = 'url("' + params.logo + '")'
</script>

<template>
  <a class="widget-product-card" ref="card" id="widget-card" :style="{'--logo': (path)}">
    <p class="card-position widget-product-card-font">
      {{params.title}}
    </p>
    <span class="card-position widget-product-card-font">
      {{params.describe}}
    </span>
    <div class="card-position widget-product-arrow">
      <img class="arrow-wh" src="../../../assets/Img/icon/link-arrow-wh.png" alt=""/>
      <img class="arrow-bk" src="../../../assets/Img/icon/link-arrow-bk.png" alt="">
    </div>
  </a>
</template>


<style scoped>

</style>