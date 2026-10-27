package com.langualaxy.Service;

import com.langualaxy.Dtos.TestOptionDTO;
import com.langualaxy.Entity.TestOption;
import com.langualaxy.Exceptions.NotFoundException;
import com.langualaxy.Repository.TestOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestOptionService {

    @Autowired
    private TestOptionRepository testOptionRepository;

    public TestOption createTestOption(TestOption testOption) {
        return testOptionRepository.save(testOption);
    }

    public List<TestOptionDTO> getAllTestOptions() {
        return testOptionRepository.findAll().stream()
                .map(o -> new TestOptionDTO(o.getId(), o.getText())).toList();
    }

    public TestOption getTestOptionById(Long testOptionId) {
        return testOptionRepository.findById(testOptionId)
                .orElseThrow(() -> new NotFoundException("TestOption", testOptionId));
    }

    public TestOption updateTestOption(Long testOptionId, TestOption updatedTestOption) {
        TestOption testOption = testOptionRepository.findById(testOptionId)
                .orElseThrow(() -> new NotFoundException("TestOption", testOptionId));
        testOption.setText(updatedTestOption.getText());
        testOption.setCorrect(updatedTestOption.isCorrect());
        return testOptionRepository.save(testOption);
    }

    public void deleteTestOption(Long testOptionId) {
        testOptionRepository.deleteById(testOptionId);
    }

    public List<TestOption> getAllTestOptionsByQuestionId(Long questionId) {
        return testOptionRepository.findByQuestionId(questionId);
    }
}
