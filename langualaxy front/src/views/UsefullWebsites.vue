<template>
    <div>
        <div class="lgx-container">
         <div class="container py-5">
        <h1>Useful websites</h1>
        <ul>
            <li><a href="https://www.bbc.com/news">Reading (BBC)</a> </li>
            <li><a href="https://www.ted.com/talks">Listening (Ted)</a></li>
            <li><a href="https://dictionary.cambridge.org/">Dictionary (Cambridge)</a></li>
            <li><a href="https://ed.ted.com/">Listening (Ted-ed)</a></li>
            <li><a href="https://time.com/">Reading (Time)</a></li>
            <li><a href="https://podcasts.google.com/">Podcasts</a></li>
        </ul>
         </div>
     </div>  
    </div>
</template>

<script>
    export default {
        
    }
</script>

<style scoped>
    a{  
        font-size: 24px;
        /* text-decoration: none; */
    }
  .lgx-container{
        color: #5930FF;
    }
   a:hover{
      color: white;
      cursor: pointer;
    }
    li{
      margin-top: 5px;
      margin-bottom: 5px;
      font-size: 18px;
      list-style: none;
    }
</style>