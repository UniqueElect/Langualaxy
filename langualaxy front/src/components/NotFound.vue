<template>
    <div>
        <div class="lgx-container">
        <div class="container py-5">
      <h1>404</h1>
      <p>Page not found</p>
      <img src="@/assets/404.jpg" class="error_img"/>
    </div>
    </div>
    </div> 
  </template>
  
  <script>
  export default {
    name: "NotFound",
  }
  </script>
  
  <style scoped>
  h1 {
    font-size: 48px;
  }
  p {
    font-size: 24px;
  }
  .error_img{
    height: calc(100vh - 463.6px)
  }
  </style>
  