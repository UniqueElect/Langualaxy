package com.langualaxy.Service;

import com.langualaxy.Dtos.TestOptionDTO;
import com.langualaxy.Entity.TestOption;
import com.langualaxy.Exceptions.NotFoundException;
import com.langualaxy.Mappers.TestOptionMapper;
import com.langualaxy.Repository.TestOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestOptionService {

    @Autowired
    private TestOptionRepository testOptionRepository;
    private final TestOptionMapper testOptionMapper;
    public TestOptionService(TestOptionMapper testOptionMapper){
        this.testOptionMapper = testOptionMapper;
    }

    public TestOptionDTO createTestOption(TestOption testOption) {
        return testOptionMapper.toDTO(testOptionRepository.save(testOption));
    }

    public List<TestOptionDTO> getAllTestOptions() {
        return testOptionRepository.findAll().stream()
                .map(o -> new TestOptionDTO(o.getId(), o.getText())).toList();
    }

    public TestOptionDTO getTestOptionById(Long testOptionId) {
        return testOptionMapper.toDTO(testOptionRepository.findById(testOptionId)
                .orElseThrow(() -> new NotFoundException("TestOption", testOptionId)));
    }

    public TestOptionDTO updateTestOption(Long testOptionId, TestOption updatedTestOption) {
        TestOption testOption = testOptionRepository.findById(testOptionId)
                .orElseThrow(() -> new NotFoundException("TestOption", testOptionId));
        testOption.setText(updatedTestOption.getText());
        testOption.setCorrect(updatedTestOption.isCorrect());
        return testOptionMapper.toDTO(testOptionRepository.save(testOption));
    }

    public void deleteTestOption(Long testOptionId) {
        testOptionRepository.deleteById(testOptionId);
    }

    public List<TestOptionDTO> getAllTestOptionsByQuestionId(Long questionId) {
        return testOptionRepository.findByQuestionId(questionId).stream().map(testOptionMapper::toDTO).toList();
    }
}
