package com.langualaxy.Service;

import com.langualaxy.Dtos.QuestionDTO;
import com.langualaxy.Dtos.TestDTO;
import com.langualaxy.Dtos.TestResult;
import com.langualaxy.Entity.Question;
import com.langualaxy.Entity.Test;
import com.langualaxy.Entity.TestOption;
import com.langualaxy.Exceptions.NotFoundException;
import com.langualaxy.Mappers.QuestionMapper;
import com.langualaxy.Mappers.TestMapper;
import com.langualaxy.Repository.QuestionRepository;
import com.langualaxy.Repository.TestOptionRepository;
import com.langualaxy.Repository.TestRepository;
import com.langualaxy.Request.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TestService {
    private final TestRepository testRepository;

    private final QuestionRepository questionRepository;

    private final TestOptionRepository testOptionRepository;
    private final TestMapper testMapper;
    private final QuestionMapper questionMapper;

    public TestService(TestRepository testRepository,
                       QuestionRepository questionRepository, TestOptionRepository testOptionRepository
            , TestMapper testMapper, QuestionMapper questionMapper) {
        this.testRepository = testRepository;
        this.testOptionRepository = testOptionRepository;
        this.questionRepository = questionRepository;
        this.testMapper = testMapper;
        this.questionMapper = questionMapper;
    }

    @Transactional
    public TestDTO createTest(TestCreationRequest testCreationRequest) {
        Test test = new Test(testCreationRequest.getName());
        testCreationRequest.getQuestions()
                .forEach(q -> test.addQuestion(questionMapper.toQuestion(q)));
        return testMapper.toDto(testRepository.save(test));
    }

    @Transactional(readOnly = true)
    public List<TestDTO> getAllTests() {
        return testRepository.findAll().stream().map(testMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public TestDTO getTestById(Long testId) {
        return testRepository.findById(testId).map(testMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Test", testId));
    }

    @Transactional
    public TestDTO updateTest(Long testId, TestUpdateRequest request) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new NotFoundException("Test", testId));
        test.setTitle(request.title());
        return testMapper.toDto(testRepository.save(test));
    }

    @Transactional
    public void deleteTest(Long testId) {
        testRepository.deleteById(testId);
    }


    private List<Question> selectRandomQuestions(List<Question> questions, int numQuestions) {
        var copy = new ArrayList<>(questions);
        Collections.shuffle(copy);
        return copy.subList(0, Math.min(copy.size(), numQuestions));
    }

    @Transactional(readOnly = true)
    public List<QuestionDTO> startTest(Long testId, int numQuestions) {
        if (!testRepository.existsById(testId)) {
            throw new NotFoundException("Test", testId);
        }
        List<Question> allQuestionsForTest = questionRepository.findByTestId(testId);
        if (allQuestionsForTest.isEmpty()) {
            return Collections.emptyList();
        }
        List<Question> selectedQuestions = selectRandomQuestions(allQuestionsForTest, numQuestions);
        var optionsByQuestionId = testOptionRepository.
                findByQuestionIdIn(selectedQuestions.stream().map(Question::getId).toList()).stream()
                .collect(Collectors.groupingBy(o -> o.getQuestion().getId()));
        return selectedQuestions.stream()
                .map(q -> questionMapper.toDTO(q, optionsByQuestionId.getOrDefault(q.getId(), List.of()))).toList();
    }

    @Transactional(readOnly = true)
    public TestResult submitTest(Long testId, TestSubmitRequest answers) {
        if (!testRepository.existsById(testId)) {
            throw new NotFoundException("Test", testId);
        }
        Map<Long, Set<Long>> correctByQuestion = testOptionRepository.findByQuestionTestIdAndCorrectTrue(testId)
                .stream().collect(Collectors.groupingBy(o -> o.getQuestion().getId(),
                        Collectors.mapping(TestOption::getId, Collectors.toSet())));
        Set<Long> correctQuestionIds = new HashSet<>();
        for (Map.Entry<Long, Set<Long>> entry : answers.getAnswers().entrySet()) {
            Set<Long> correct = correctByQuestion.get(entry.getKey());
            if (correct != null && correct.equals(entry.getValue())) {
                correctQuestionIds.add(entry.getKey());
            }
        }
        return new TestResult(correctQuestionIds);
    }
}

