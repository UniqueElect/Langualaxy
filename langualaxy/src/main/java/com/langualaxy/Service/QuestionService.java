package com.langualaxy.Service;

import com.langualaxy.Dtos.QuestionDTO;
import com.langualaxy.Entity.Question;
import com.langualaxy.Exceptions.NotFoundException;
import com.langualaxy.Mappers.QuestionMapper;
import com.langualaxy.Repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionService(QuestionMapper questionMapper, QuestionRepository questionRepository) {
        this.questionMapper = questionMapper;
        this.questionRepository = questionRepository;
    }

    @Transactional
    public QuestionDTO createQuestion(Question question) {
        return questionMapper.toDTO(questionRepository.save(question));
    }

    @Transactional(readOnly = true)
    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAllWithOptions().stream().map(questionMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public QuestionDTO getQuestionById(Long questionId) {
        return questionMapper.toDTO(questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question", questionId)));
    }

    @Transactional
    public QuestionDTO updateQuestion(Long questionId, Question updatedQuestion) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question", questionId));
        question.setText(updatedQuestion.getText());
        return questionMapper.toDTO(questionRepository.save(question));
    }

    @Transactional
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }
}
