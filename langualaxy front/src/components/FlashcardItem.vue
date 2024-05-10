
   <template>
    <div class="lgx-container">
      <div class="container py-5">
    <div v-if="flashcards.length > 0">
      <p>Click image to show/hide word</p>
      <img @click="toggleWord" id="image" :src="'data:image/jpeg;base64,' + flashcards[currentIndex].image" alt="Image">
      <div>
      <h2 v-if="showWord">{{ flashcards[currentIndex].word }}</h2>
      </div>
      <p>{{ flashcards[currentIndex].translation }}</p>
      <div class="ctr">
      <div class="btns">
      <button class="btn btn-secondary" @click="prevCard">prev</button>
      <button class="btn btn-secondary" @click="nextCard">next</button>
      </div>
     </div>
    </div>
    <div v-else>
      <p>Loading flashcards...</p>
    </div>
  </div>
  </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  
  export default {
   
    data() {
      return {
        showWord: false,
        flashcards: ref([]),
        currentIndex: 0,
      };
    },
    async mounted() {
      this.fetchFlashcards();
    },
    methods: {
        async fetchFlashcards() {
  try {
    const themeId = this.$route.params.themeId; 
    const response = await fetch(`http://localhost:8080/api/flashcards/get/${themeId}`);
    const data = await response.json();
    this.flashcards = data;
    console.log(this.flashcards);
  } catch (error) {
    console.error(error);
  }
},
      prevCard() {
        this.currentIndex = (this.currentIndex - 1 + this.flashcards.length) % this.flashcards.length;
      },
      nextCard() {
        this.currentIndex = (this.currentIndex + 1) % this.flashcards.length;
      },
      toggleWord() {
      this.showWord = !this.showWord;
    },
    },
  };
  </script>
  <style scoped>
#image{
    height: 300px;
    padding-bottom: 10px;
}
#hoverText {
  display: none
}
#image:hover {
  cursor: pointer;
  transform: scale(1.1);
}
.lgx-container{
  min-height: calc(100vh - 108px);
}
.btns{
  display: flex;
  justify-content: center;
}
.btn{
  margin-right: 100px;
  margin-left: 100px;
}
h2{
  margin-top: 10px;
}
</style>