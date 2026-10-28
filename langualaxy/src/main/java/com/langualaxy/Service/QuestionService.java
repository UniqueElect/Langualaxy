package com.langualaxy.Service;

import com.langualaxy.Dtos.QuestionDTO;
import com.langualaxy.Entity.Question;
import com.langualaxy.Exceptions.NotFoundException;
import com.langualaxy.Mappers.QuestionMapper;
import com.langualaxy.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    public QuestionService(QuestionMapper questionMapper){
        this.questionMapper = questionMapper;
    }
    public QuestionDTO createQuestion(Question question) {
        return questionMapper.toDTO(questionRepository.save(question));
    }
    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAllWithOptions().stream().map(questionMapper::toDTO).toList();
    }
    public QuestionDTO getQuestionById(Long questionId) {
        return questionMapper.toDTO(questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question", questionId)));
    }
    public QuestionDTO updateQuestion(Long questionId, Question updatedQuestion) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question", questionId));
        question.setText(updatedQuestion.getText());
        return questionMapper.toDTO(questionRepository.save(question));
    }
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }
}
