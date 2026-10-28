package com.langualaxy.Service;

import com.langualaxy.Dtos.TestOptionDTO;
import com.langualaxy.Entity.TestOption;
import com.langualaxy.Exceptions.NotFoundException;
import com.langualaxy.Mappers.TestOptionMapper;
import com.langualaxy.Repository.TestOptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestOptionService {

    private final TestOptionRepository testOptionRepository;
    private final TestOptionMapper testOptionMapper;

    public TestOptionService(TestOptionMapper testOptionMapper, TestOptionRepository testOptionRepository) {
        this.testOptionMapper = testOptionMapper;
        this.testOptionRepository = testOptionRepository;
    }

    @Transactional
    public TestOptionDTO createTestOption(TestOption testOption) {
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
    public TestOptionDTO updateTestOption(Long testOptionId, TestOption updatedTestOption) {
        TestOption testOption = testOptionRepository.findById(testOptionId)
                .orElseThrow(() -> new NotFoundException("TestOption", testOptionId));
        testOption.setText(updatedTestOption.getText());
        testOption.setCorrect(updatedTestOption.isCorrect());
        return testOptionMapper.toDTO(testOptionRepository.save(testOption));
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
