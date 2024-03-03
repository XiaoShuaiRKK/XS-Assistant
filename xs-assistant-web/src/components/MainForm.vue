<script setup>

</script>

<template>
  <div class="main-all-box">
    <el-container>
      <el-header>
        Header
      </el-header>
      <el-container>
        <el-aside id="main-aside">
          Aside
        </el-aside>
        <el-container>
          <el-main>
            Main
          </el-main>
          <el-footer>
            Footer
          </el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
.main-all-box{
  position: absolute;
  width: 100%;
}
#main-aside{
  width: 20%;
}
</style>