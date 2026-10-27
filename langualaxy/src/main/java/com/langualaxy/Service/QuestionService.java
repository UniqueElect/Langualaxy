package com.langualaxy.Service;

import com.langualaxy.Entity.Question;
import com.langualaxy.Exceptions.NotFoundException;
import com.langualaxy.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Question getQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question", questionId));
    }
    public Question updateQuestion(Long questionId, Question updatedQuestion) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question", questionId));
        question.setText(updatedQuestion.getText());
        return questionRepository.save(question);
    }
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }
}
