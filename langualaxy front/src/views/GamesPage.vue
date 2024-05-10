

<template>
  <div>
    <div class="lgx-container">
      <div class="container py-5">
    <h1>Flashcard Themes</h1>
    <ul>
      <li v-for="theme in themes" :key="theme.id" @click="goToCards(theme.id)">
        {{ theme.text }}
      </li>
    </ul>
    </div> 
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const themes = ref([]);
    
    const fetchThemes = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/themes/all');
        const data = await response.json();
        themes.value = data;
      } catch (error) {
        console.error(error);
      }
    };
    
    const router = useRouter();
    const goToCards = (themeId) => {
      router.push({ name: 'Flashcard', params: { themeId } });
    };

    
    fetchThemes();
    
    return {
      themes,
      goToCards
    };
  }
}
</script>

<style scoped>
  h2{
    color: #5930FF;
  }
  p{
    font-size: 18px;
  }
  /* .flashcard-container {
  display: flex;
  flex-direction: column;
  width: 500px;
  margin: 0 auto;
}

.themes-list {
  display: flex;
  flex-wrap: wrap;
  margin-bottom: 20px;
}

.theme-item {
  padding: 10px;
  border: 1px solid #ccc;
  margin: 5px;
  cursor: pointer;
}

.flashcards-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.flashcard-item {
  display: flex;
  align-items: center;
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
} */
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
    .lgx-container{
        color: #5930FF;
        background-color: #0F1211;
        min-height: calc(100vh - 114px - 136px);
    }
</style> 