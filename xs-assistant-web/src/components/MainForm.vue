<script setup>
import RedLine from './BackgroundLine/line_red.vue'
import YellowLine from './BackgroundLine/line_yellow.vue'
import GreenLine from './BackgroundLine/line_green.vue'
import BlueLine from './BackgroundLine/line_blue.vue'
</script>

<template>
  <div class="main-all-box">
    <el-container direction="vertical" class="main-all-container">
      <el-aside width="20%" id="main-aside">
        <div class="nav-icon">
          <img src="../assets/Logo/XS-LOGO.png" alt="">
        </div>
        <div class="nav-header-btn">
          <div class="nav-header-btn_inner">
            <div/>
            <div/>
          </div>
        </div>
        <div class="nav-header-share">
          <p>
            Join us
          </p>
        </div>
      </el-aside>
      <el-container class="main-main-box">
        <div>
          <RedLine class="main-background red"/>
          <YellowLine class="main-background yellow"/>
          <GreenLine class="main-background green"/>
          <BlueLine class="main-background blue"/>
          <div class="main-title">
            <p>XS-Assistant</p>
          </div>
        </div>

      </el-container>

    </el-container>
  </div>
</template>

<style scoped>
.main-all-box{
  position: absolute;
  width: 100%;
}
.main-all-container{
  position: absolute;
  width: 100%;
  height: 100%;
}
.main-all-container el-header{
  height: 50px;
}
el-aside{
  display: inline-flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  width: 10%;
  height: 100%;
  padding: 0 1%;
  background: black;
  .nav-icon{
    margin: 0 auto;
    width: 50%;
    img{
      width: 100%;
    }
  }
  .nav-header-btn{
    width: 100%;
    height: 40%;
  }
  .nav-header-share{
    display: flex;
    justify-content: space-around;
    flex-direction: column;
  }
}
.nav-header-share p{
  font-size: 20px;
  writing-mode: vertical-lr;
}
.nav-header-btn_inner{
  height: 35px;
  width: auto;
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  display: flex;
  justify-content: center;
  div{
    width: 2px;
    height: 100%;
    margin: 0 2px;
    border: white 2px solid;
  }
}
.main-main-box{
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
.main-title{
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  z-index: 5;
}
.main-title p{
  font-size: 100px;
  font-weight: bolder;
  color: #ffffff;
}
</style>