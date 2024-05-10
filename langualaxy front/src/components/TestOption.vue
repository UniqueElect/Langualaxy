<template>
    <div>
      <ul>
        <li v-for="option in options" :key="option.id" class="option-item">
          <label class="option-label">
            <input
              type="radio"
              :id="'option-' + option.id"
              :value="option.id"
              v-model="selectedOptionId"
              @change="handleRadioChange(option)"
            >
            {{ option.text }}
          </label>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      options: {
        type: Array,
        required: true
      }
    },
    data() {
      return {
        selectedOptionId: null
      };
    },
    methods: {
      handleRadioChange(selectedOption) {
        this.$emit('select', {
          id: selectedOption.id,
          correct: selectedOption.correct
        });
      }
    }
  };
  </script>
  
  <style>
    li{
    margin-top: 10px;
    margin-bottom: 10px;
    text-align: left;
    list-style: none;
    font-size: 20px;
  }
  .option-label {
    cursor: pointer;
  }
  </style>
  