package com.langualaxy.Service;

import com.langualaxy.Dtos.QuestionDTO;
import com.langualaxy.Entity.Question;
import com.langualaxy.Entity.Test;
import com.langualaxy.Exceptions.NotFoundException;
import com.langualaxy.Mappers.QuestionMapper;
import com.langualaxy.Repository.QuestionRepository;
import com.langualaxy.Repository.TestRepository;
import com.langualaxy.Request.QuestionCreationRequest;
import com.langualaxy.Request.QuestionUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;
    private final QuestionMapper questionMapper;

    public QuestionService(QuestionMapper questionMapper, QuestionRepository questionRepository, TestRepository testRepository) {
        this.questionMapper = questionMapper;
        this.questionRepository = questionRepository;
        this.testRepository = testRepository;
    }

    @Transactional
    public QuestionDTO createQuestion(QuestionCreationRequest request) {
        Test test = testRepository.findById(request.testId())
                .orElseThrow(() -> new NotFoundException("Test", request.testId()));
        Question question = new Question(request.text(), test);
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
    public QuestionDTO updateQuestion(Long questionId, QuestionUpdateRequest request) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question", questionId));
        question.setText(request.text());
        return questionMapper.toDTO(question);
    }

    @Transactional
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }
}
