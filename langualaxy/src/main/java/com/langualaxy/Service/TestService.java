package com.langualaxy.Service;

import com.langualaxy.Entity.Question;
import com.langualaxy.Entity.Test;
import com.langualaxy.Entity.TestOption;
import com.langualaxy.Repository.QuestionRepository;
import com.langualaxy.Repository.TestOptionRepository;
import com.langualaxy.Repository.TestRepository;
import com.langualaxy.Request.OptionRequest;
import com.langualaxy.Request.QuestionRequest;
import com.langualaxy.Request.TestCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestOptionRepository testOptionRepository;

    public Test createTest(TestCreationRequest testCreationRequest) {
        Test test = new Test();
        test.setTitle(testCreationRequest.getName());

        test = testRepository.save(test);

        for (QuestionRequest questionRequest : testCreationRequest.getQuestions()) {
            Question question = new Question();
            question.setText(questionRequest.getText());
            question.setTest(test);
            question = questionRepository.save(question);

            for (OptionRequest optionRequest : questionRequest.getOptions()) {
                TestOption option = new TestOption();
                option.setText(optionRequest.getText());
                option.setCorrect(optionRequest.isCorrect());
                option.setQuestion(question);
                testOptionRepository.save(option);
            }
        }

        return test;
    }
//    public Test createTest(Test test) {
//        return testRepository.save(test);
//    }
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }
    public Optional<Test> getTestById(Long testId) {
        return testRepository.findById(testId);
    }
    public Test updateTest(Long testId, Test updatedTest) {
        Optional<Test> existingTestOptional = testRepository.findById(testId);
        if (existingTestOptional.isPresent()) {
            Test existingTest = existingTestOptional.get();
            existingTest.setTitle(updatedTest.getTitle());
            return testRepository.save(existingTest);
        } else {
            return null;
        }
    }
    public void deleteTest(Long testId) {
        testRepository.deleteById(testId);
    }
    public List<Question> selectRandomQuestions(Long testId, int numQuestions) {
        List<Question> allQuestionsForTest = questionRepository.findByTestId(testId);
        if (allQuestionsForTest.isEmpty()) {
            return Collections.emptyList();
        }
        List<Question> copyQuestions = new ArrayList<>(allQuestionsForTest);
        List<Question> selectedQuestions = new ArrayList<>();
        Random random = new Random();
        while (!copyQuestions.isEmpty() && selectedQuestions.size() < numQuestions) {
            int randomIndex = random.nextInt(copyQuestions.size());
            selectedQuestions.add(copyQuestions.get(randomIndex));
            copyQuestions.remove(randomIndex);
        }
        return selectedQuestions;
    }

}

