<template>
  <div class="lgx-container">
    <div class="container py-3">
      <div class="text-container">
      <h1>Under Construction</h1>
      <p>Unfortunately, this page is not ready yet. Turn back later!</p>
    </div>
  </div>
</div>
</template>

<script>
  export default {
    
  }
</script>

<style scoped>
.text-container{
  border: 1px solid #CDFF03;
  border-radius: 5px;
  padding: 10px;
  background-color: #0f1211bd ;
  display: inline-block;
}
  
  h1{
    color: #5930FF;
  }
  .lgx-container{
    min-height: calc(100vh - 108px);
    background-image: url('@/assets/under_constructuin2.png');
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
    /* height: max-content; */
  }
</style>