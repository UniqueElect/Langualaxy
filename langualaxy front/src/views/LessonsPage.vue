<template>
    <div>
        <div class="lgx-container">
            <div class="container py-5">
            <h1>Learning materials</h1>
            <p>Here you can donwload PDF files with grammar rules</p>
            <ul>
                <li v-for="file in files" :key="file.fileName">
                <a :href="`http://localhost:8080/api/files/${file.id}`" target="_blank" @click.prevent="openFile(file.id, file.fileName)">{{ file.fileName }}</a>
                </li>
            </ul>
            </div>
            </div>
            </div> 
    </template>
  
  <script>
  export default {
    data() {
      return {
        files: []
      };
    },
    methods: {
      async fetchFiles() {
        try {
          const response = await fetch('http://localhost:8080/api/files/all');
          const data = await response.json();
          this.files = data;
        } catch (error) {
          console.error('Error fetching files:', error);
        }
      },
      openFile(id) {
        const fileURL = `http://localhost:8080/api/files/${id}`;
        window.open(fileURL, '_blank');
      }
    },
    created() {
      this.fetchFiles();
    }
  };
  </script>

<style scoped>
     h1{
      color: #5930FF;
    }
    p{
        font-size: 20px;
    }
</style>