package com.langualaxy.Service;

import com.langualaxy.Dtos.TestOptionDTO;
import com.langualaxy.Entity.Question;
import com.langualaxy.Entity.TestOption;
import com.langualaxy.Exceptions.NotFoundException;
import com.langualaxy.Mappers.TestOptionMapper;
import com.langualaxy.Repository.QuestionRepository;
import com.langualaxy.Repository.TestOptionRepository;
import com.langualaxy.Request.OptionCreationRequest;
import com.langualaxy.Request.OptionUpdateRequest;
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
        TestOption testOption = new TestOption(request.text(), request.correct(), question);
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
