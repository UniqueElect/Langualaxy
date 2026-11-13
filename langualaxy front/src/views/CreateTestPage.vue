<template>
  <div class="lgx-container">
    <div class="container py-5 create-test">
      <h1 class="topic">Create a test</h1>
      <div id="delimetr"></div>

      <form @submit.prevent="saveTest" novalidate>
        <label for="test-title" class="field-label">Test title</label>
        <input
          id="test-title"
          v-model="title"
          type="text"
          class="form-control lgx-input"
          :class="{ 'is-invalid': submitted && !title.trim() }"
          placeholder="For example: Present Simple"
        >

        <div
          v-for="(question, qIndex) in questions"
          :key="question.key"
          class="question-card"
        >
          <div class="question-header">
            <span class="question-number">Question {{ qIndex + 1 }}</span>
            <button
              v-if="questions.length > 1"
              type="button"
              class="btn btn-link remove-link"
              @click="removeQuestion(qIndex)"
            >
              Remove question
            </button>
          </div>

          <input
            v-model="question.text"
            type="text"
            class="form-control lgx-input"
            :class="{ 'is-invalid': submitted && !question.text.trim() }"
            placeholder="Question text, e.g. She ___ to school every day."
          >

          <p class="hint">Answer options — mark the correct one</p>

          <div
            v-for="(option, oIndex) in question.options"
            :key="option.key"
            class="option-row"
          >
            <input
              :id="'correct-' + option.key"
              type="radio"
              class="form-check-input correct-radio"
              :name="'correct-' + question.key"
              :checked="question.correctKey === option.key"
              :title="'Mark option ' + (oIndex + 1) + ' as correct'"
              @change="question.correctKey = option.key"
            >
            <input
              v-model="option.text"
              type="text"
              class="form-control lgx-input"
              :class="{ 'is-invalid': submitted && !option.text.trim() }"
              :placeholder="'Option ' + (oIndex + 1)"
            >
            <button
              v-if="question.options.length > 2"
              type="button"
              class="btn remove-option"
              :aria-label="'Remove option ' + (oIndex + 1)"
              @click="removeOption(question, oIndex)"
            >
              ×
            </button>
          </div>

          <button type="button" class="btn btn-outline-light btn-sm" @click="addOption(question)">
            + Add option
          </button>
        </div>

        <button type="button" class="btn btn-outline-light add-question" @click="addQuestion">
          + Add question
        </button>

        <ul v-if="errors.length || serverError" class="errors">
          <li v-for="error in errors" :key="error">{{ error }}</li>
          <li v-if="serverError">{{ serverError }}</li>
        </ul>

        <div class="actions">
          <button type="submit" id="save" class="btn" :disabled="saving">
            {{ saving ? 'Saving…' : 'Save test' }}
          </button>
          <router-link to="/tests" class="btn btn-outline-light">Cancel</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
let nextKey = 1;

function newOption() {
  return { key: nextKey++, text: '' };
}

function newQuestion() {
  return { key: nextKey++, text: '', correctKey: null, options: [newOption(), newOption()] };
}

export default {
  data() {
    return {
      title: '',
      questions: [newQuestion()],
      submitted: false,
      saving: false,
      serverError: ''
    };
  },
  computed: {
    // After the first save attempt, the list updates as the form is fixed
    errors() {
      return this.submitted ? this.validate() : [];
    }
  },
  methods: {
    addQuestion() {
      this.questions.push(newQuestion());
    },
    removeQuestion(index) {
      this.questions.splice(index, 1);
    },
    addOption(question) {
      question.options.push(newOption());
    },
    removeOption(question, index) {
      const [removed] = question.options.splice(index, 1);
      if (question.correctKey === removed.key) {
        question.correctKey = null;
      }
    },
    validate() {
      const errors = [];
      if (!this.title.trim()) {
        errors.push('Enter a test title.');
      }
      this.questions.forEach((question, i) => {
        const number = i + 1;
        if (!question.text.trim()) {
          errors.push(`Question ${number}: enter the question text.`);
        }
        if (question.options.some(option => !option.text.trim())) {
          errors.push(`Question ${number}: fill in every answer option.`);
        }
        if (question.correctKey === null) {
          errors.push(`Question ${number}: mark the correct answer.`);
        }
      });
      return errors;
    },
    toRequest() {
      return {
        name: this.title.trim(),
        questions: this.questions.map(question => ({
          text: question.text.trim(),
          options: question.options.map(option => ({
            text: option.text.trim(),
            correct: option.key === question.correctKey
          }))
        }))
      };
    },
    async saveTest() {
      this.submitted = true;
      this.serverError = '';
      if (this.errors.length) {
        return;
      }

      this.saving = true;
      try {
        const response = await fetch('http://localhost:8080/api/tests', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(this.toRequest())
        });
        if (!response.ok) {
          throw new Error(`Server responded with ${response.status}`);
        }
        const created = await response.json();
        console.log('Test created:', created);
        this.$router.push('/tests');
      } catch (error) {
        console.error('Error creating the test:', error);
        this.serverError = 'Could not save the test. Is the backend running?';
      } finally {
        this.saving = false;
      }
    }
  }
};
</script>

<style scoped>
.create-test {
  max-width: 760px;
  text-align: left;
}
.topic {
  color: #CDFF03;
  text-align: center;
}
#delimetr {
  margin-top: 20px;
  height: 3px;
  background-color: #5930FF;
  margin-bottom: 30px;
}
.field-label,
.question-number {
  font-family: 'Patua One', sans-serif;
  font-size: 20px;
  color: #CDFF03;
  margin-bottom: 8px;
  display: inline-block;
}
.lgx-input {
  background-color: #1b1c1b;
  color: white;
  border: 1px solid #5930FF;
}
.lgx-input.is-invalid {
  border-color: #ff6b6b;
}
.lgx-input::placeholder {
  color: #8a8a8a;
}
.lgx-input:focus {
  background-color: #1b1c1b;
  color: white;
  border-color: #CDFF03;
  box-shadow: 0 0 0 0.2rem rgba(205, 255, 3, 0.25);
}
.question-card {
  margin-top: 28px;
  padding: 20px;
  border: 2px solid #5930FF;
  border-radius: 8px;
  background-color: rgba(15, 18, 17, 0.85);
}
.question-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.remove-link {
  color: #AFAFAF;
  padding: 0;
}
.remove-link:hover {
  color: white;
}
.hint {
  margin: 16px 0 8px;
  color: #AFAFAF;
  font-size: 15px;
}
.option-row {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 10px;
}
.correct-radio {
  flex-shrink: 0;
  width: 1.3em;
  height: 1.3em;
  margin: 0;
  cursor: pointer;
  background-color: #1b1c1b;
  border-color: #5930FF;
}
.correct-radio:checked {
  background-color: #CDFF03;
  border-color: #CDFF03;
}
.remove-option {
  flex-shrink: 0;
  color: #AFAFAF;
  font-size: 22px;
  line-height: 1;
  padding: 0 6px;
}
.remove-option:hover {
  color: white;
}
.add-question {
  margin-top: 20px;
}
.errors {
  margin-top: 24px;
  padding-left: 0;
  color: #ff6b6b;
}
.errors li {
  list-style: none;
  font-size: 16px;
  margin: 4px 0;
  text-align: left;
}
.actions {
  margin-top: 28px;
  display: flex;
  gap: 12px;
}
#save {
  border: none;
  color: white;
  font-family: 'Patua One', sans-serif;
  background-color: #5930FF;
}
#save:hover {
  outline: white 1px solid;
}
#save:active {
  transform: scale(0.95);
}
#save:disabled {
  opacity: 0.6;
}
</style>
