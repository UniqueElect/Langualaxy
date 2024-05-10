<template>
    <div>
      <div class="lgx-container">
        <div class="container py-5">
          <p v-if="!testFinished">Question {{ currentQuestionIndex }} / {{ totalQuestions }}</p>
      <div v-if="!testFinished"> 
        <TestQuestion v-if="currentQuestion" :question="currentQuestion" :options="currentQuestion.options || []" @select="selectOption"></TestQuestion>
        <button id="answer" class="btn btn-secondary" v-if="currentQuestion" @click="submitAnswer">Answer</button>
      </div>
      <div v-if="testFinished">
        <p>You have finished the test!</p> 
      <p>You have {{ correctAnswersCount }} correct answers from {{ totalQuestions }} </p>
        <button id="finish" class="btn btn-success" @click="finishTest">Finish test</button>
      </div>
    </div>
  </div>
</div>
</template>
  
  <script>
  import TestQuestion from '@/components/TestQuestion.vue';
  
  export default {
    components: { TestQuestion },
    props: {
      testId: {
        type: String,
        required: true
      }
    },
    data() {
      return {
        currentQuestion: null,
        selectedOption: null,
        correctAnswersCount: 0,
        questions: [], 
        testFinished: false 
      };
    },
    created() {
      this.startTest(this.testId);
    },
    computed: {
    currentQuestionIndex() {
      return this.questions.indexOf(this.currentQuestion) + 1;
    },
    totalQuestions(){
      return this.questions.length;
    }
  },
    methods: {
      startTest(testId) {
        console.log('Starting test with ID:', testId);
        fetch(`http://localhost:8080/api/tests/${testId}/start`)
          .then(response => response.json())
          .then(data => {
            console.log('Received questions:', data);
            if (Array.isArray(data) && data.length > 0) {
              this.questions = data; 
              this.currentQuestion = this.questions[0];
              this.fetchOptionsForQuestion(this.currentQuestion.id); 
            } else {
              console.error('No questions available for the test');
            }
          })
          .catch(error => {
            console.error('Error at the start of the test:', error);
          });
      },
      fetchOptionsForQuestion(questionId) {
        fetch(`http://localhost:8080/api/test-options/question/${questionId}`)
          .then(response => response.json())
          .then(data => {
            console.log('Received answer options for the question:', data);
            this.currentQuestion.options = data;
          })
          .catch(error => {
            console.error('Error fetching answer options:', error);
          });
      },
      selectOption(option) {
        this.selectedOption = option;
      },
      submitAnswer() {
        console.log('Selected answer:', this.selectedOption);
        if (this.selectedOption) {
          if (this.selectedOption.correct) {
            this.correctAnswersCount++;
          }
          this.moveToNextQuestion();
          this.selectedOption = null;
        } else {
          alert('No answer option selected');
        }
      },
      moveToNextQuestion() {
        const currentIndex = this.questions.indexOf(this.currentQuestion);
        if (currentIndex !== -1 && currentIndex < this.questions.length - 1) {
          this.currentQuestion = this.questions[currentIndex + 1];
          this.fetchOptionsForQuestion(this.currentQuestion.id);
        } else {
          console.warn('Failed to proceed to the next question');
          this.testFinished = true; 
        }
      },
      finishTest() {
        this.$router.push('/tests');
      }
    }
  };
  </script>
  <style scoped>
    p{
      color: #5930FF;
      font-size: 20px;
    }
    #answer{
      border: none;
      color:#1b1c1b;
      font-family: 'Patua One', sans-serif;
      background-color: #5930FF;
    }
    #answer:hover{
      border: white 1px solid;
    }
    #answer:active{
      transform: scale(0.9);
    }
    #finish:active{
        transform: scale(0.9);
    }
  </style>