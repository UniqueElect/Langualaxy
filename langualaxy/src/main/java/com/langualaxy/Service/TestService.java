package com.langualaxy.Service;

import com.langualaxy.Dtos.QuestionDTO;
import com.langualaxy.Dtos.TestOptionDTO;
import com.langualaxy.Entity.Question;
import com.langualaxy.Entity.Test;
import com.langualaxy.Entity.TestOption;
import com.langualaxy.Exceptions.NotFoundException;
import com.langualaxy.Repository.QuestionRepository;
import com.langualaxy.Repository.TestOptionRepository;
import com.langualaxy.Repository.TestRepository;
import com.langualaxy.Request.OptionRequest;
import com.langualaxy.Request.QuestionRequest;
import com.langualaxy.Request.TestCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test getTestById(Long testId) {
        return testRepository.findById(testId)
                .orElseThrow(() -> new NotFoundException("Test", testId));
    }

    public Test updateTest(Long testId, Test updatedTest) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new NotFoundException("Test", testId));
        test.setTitle(updatedTest.getTitle());
        return testRepository.save(test);
    }

    public void deleteTest(Long testId) {
        testRepository.deleteById(testId);
    }

    public List<Question> selectRandomQuestions(List<Question> questions, int numQuestions) {
        var copy = new ArrayList<>(questions);
        Collections.shuffle(copy);
        return copy.subList(0, Math.min(copy.size(), numQuestions));
    }

    @Transactional(readOnly = true)
    public List<QuestionDTO> startTest(Long testId, int numQuestions) {
        List<Question> allQuestionsForTest = questionRepository.findByTestId(testId);
        if (allQuestionsForTest.isEmpty()) {
            return Collections.emptyList();
        }
        List<Question> selectedQuestions = selectRandomQuestions(allQuestionsForTest, numQuestions);
        var optionsByQuestionId = testOptionRepository.
                findByQuestionIdIn(selectedQuestions.stream().map(Question::getId).toList()).stream()
                .collect(Collectors.groupingBy(o -> o.getQuestion().getId()));

        return selectedQuestions.stream().map(q -> new QuestionDTO(q.getId(), q.getText(),
                optionsByQuestionId.getOrDefault(q.getId(), List.of()).stream()
                        .map(o -> new TestOptionDTO(o.getId(), o.getText())).toList())).toList();
    }
}

