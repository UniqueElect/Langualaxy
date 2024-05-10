<template>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Patua+One&display=swap">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Jockey+One&display=swap">
  <div id="app">
    <AppHeader />
    <router-view />
    <AppFooter />
</div>
</template>

<script>
import AppHeader from './components/AppHeader.vue';
import AppFooter from './components/AppFooter.vue';

export default {
  name: 'App',
  components: {
    AppHeader,
    AppFooter
  }
}
</script>

<style>

html{
  background-color: #212529;
}
.logo {
  width: 75px;
  height: 75px;
}
.lgx-container{
        text-align: center;
        display: flex;
        align-content: center;
        color: white;
        background-image: url('@/assets/background_stars.png'); 
        /* background-color: #0F1211; */
        min-height: calc(100vh - 114px - 136px);
    }
</style>
