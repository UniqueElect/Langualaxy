package com.langualaxy.service;

import com.langualaxy.dto.TestOptionDTO;
import com.langualaxy.entity.Question;
import com.langualaxy.entity.TestOption;
import com.langualaxy.exception.NotFoundException;
import com.langualaxy.mapper.TestOptionMapper;
import com.langualaxy.repository.QuestionRepository;
import com.langualaxy.repository.TestOptionRepository;
import com.langualaxy.request.OptionCreationRequest;
import com.langualaxy.request.OptionUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestOptionService {

    private final TestOptionRepository testOptionRepository;
    private final QuestionRepository questionRepository;
    private final TestOptionMapper testOptionMapper;

    public TestOptionService(TestOptionMapper testOptionMapper, TestOptionRepository testOptionRepository, QuestionRepository questionRepository) {
        this.testOptionMapper = testOptionMapper;
        this.testOptionRepository = testOptionRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional
    public TestOptionDTO createTestOption(OptionCreationRequest request) {
        Question question = questionRepository.findById(request.questionId())
                .orElseThrow(() -> new NotFoundException("Question", request.questionId()));
        TestOption testOption = new TestOption(request.text(), request.correct());
        question.addOption(testOption);
        return testOptionMapper.toDTO(testOptionRepository.save(testOption));
    }

    @Transactional(readOnly = true)
    public List<TestOptionDTO> getAllTestOptions() {
        return testOptionRepository.findAll().stream().map(testOptionMapper::toDTO).toList();

    }

    @Transactional(readOnly = true)
    public TestOptionDTO getTestOptionById(Long testOptionId) {
        return testOptionMapper.toDTO(testOptionRepository.findById(testOptionId)
                .orElseThrow(() -> new NotFoundException("TestOption", testOptionId)));
    }

    @Transactional
    public TestOptionDTO updateTestOption(Long testOptionId, OptionUpdateRequest request) {
        TestOption testOption = testOptionRepository.findById(testOptionId)
                .orElseThrow(() -> new NotFoundException("TestOption", testOptionId));
        testOption.setText(request.text());
        testOption.setCorrect(request.correct());
        return testOptionMapper.toDTO(testOption);
    }

    @Transactional
    public void deleteTestOption(Long testOptionId) {
        testOptionRepository.deleteById(testOptionId);
    }

    @Transactional(readOnly = true)
    public List<TestOptionDTO> getAllTestOptionsByQuestionId(Long questionId) {
        return testOptionRepository.findByQuestionId(questionId).stream().map(testOptionMapper::toDTO).toList();
    }
}
