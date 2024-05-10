<template>
    <body>
    <div class="lgx-container">
      <div class="container py-5">
      <h1 class="topic">English tests</h1>
      <div id="delimetr"></div>
      <ul>
        <li v-for="test in tests" :key="test.id" @click="startTest(test.id)">
          {{ test.title }}
        </li>
      </ul>
      </div>
    </div>
    </body>
  </template>
  
  <script>
  import { ref } from 'vue';
  import { useRouter } from 'vue-router';
  
  export default {
    setup() {
      const tests = ref([]);
  
      const fetchTests = () => {
        fetch('http://localhost:8080/api/tests/all')
          .then(response => response.json())
          .then(data => {
            console.log('List of tests fetched:', data);
            tests.value = data;
          })
          .catch(error => {
            console.error('Error while retrieving the list of tests:', error);
          });
      };
  
      const router = useRouter();
      const startTest = (testId) => {
        router.push({ name: 'TestPage', params: { testId } }); 
      };
  
      fetchTests(); 
  
      return {
        tests,
        startTest
      };
    }
  };
  </script>
<style scoped>
  .lgx-container{
        color: #5930FF;
        /* background-color: #0F1211; */
        min-height: calc(100vh - 114px - 136px);
    }
   #delimetr{
        margin-top: 20px;
        height: 3px;
        background-color: #5930FF;
        margin-bottom: 20px;
    }
    .topic{
        color: #CDFF03;
    }
    li:hover{
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