package com.langualaxy.Service;

import com.langualaxy.Entity.TestOption;
import com.langualaxy.Repository.TestOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestOptionService {

    @Autowired
    private TestOptionRepository testOptionRepository;
    public TestOption createTestOption(TestOption testOption) {
        return testOptionRepository.save(testOption);
    }
    public List<TestOption> getAllTestOptions() {
        return testOptionRepository.findAll();
    }
    public Optional<TestOption> getTestOptionById(Long testOptionId) {
        return testOptionRepository.findById(testOptionId);
    }
    public TestOption updateTestOption(Long testOptionId, TestOption updatedTestOption) {
        Optional<TestOption> existingTestOptionOptional = testOptionRepository.findById(testOptionId);
        if (existingTestOptionOptional.isPresent()) {
            TestOption existingTestOption = existingTestOptionOptional.get();
            existingTestOption.setText(updatedTestOption.getText());
            existingTestOption.setCorrect(updatedTestOption.isCorrect());
            return testOptionRepository.save(existingTestOption);
        } else {
            return null;
        }
    }
    public void deleteTestOption(Long testOptionId) {
        testOptionRepository.deleteById(testOptionId);
    }
    public List<TestOption> getAllTestOptionsByQuestionId(Long questionId) {
        return testOptionRepository.findByQuestionId(questionId);
    }
}
