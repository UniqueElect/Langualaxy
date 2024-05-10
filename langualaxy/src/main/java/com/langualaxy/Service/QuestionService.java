package com.langualaxy.Service;

import com.langualaxy.Entity.Question;
import com.langualaxy.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    public Optional<Question> getQuestionById(Long questionId) {
        return questionRepository.findById(questionId);
    }
    public Question updateQuestion(Long questionId, Question updatedQuestion) {
        Optional<Question> existingQuestionOptional = questionRepository.findById(questionId);
        if (existingQuestionOptional.isPresent()) {
            Question existingQuestion = existingQuestionOptional.get();
            existingQuestion.setText(updatedQuestion.getText());
            return questionRepository.save(existingQuestion);
        } else {
            return null;
        }
    }
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }
}
