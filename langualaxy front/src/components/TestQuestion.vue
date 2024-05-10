<template>
  <div>
    <p>{{ question.text }}</p>
    <test-option v-if="question.options" :options="question.options" @select="selectOption"></test-option>
  </div>
</template>

<script>
import TestOption from '@/components/TestOption.vue';

export default {
  components: {
    TestOption
  },
  props: {
    question: {
      type: Object,
      required: true
    }
  },
  methods: {
    selectOption(option) {
      this.$emit('select', option);
    }
  }
};
</script>
<style scoped>
  p{
    font-size: 20px;
  }
</style>