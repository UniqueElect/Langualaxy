package com.langualaxy.mapper;

import com.langualaxy.dto.TestDTO;
import com.langualaxy.entity.Test;
import com.langualaxy.request.TestCreationRequest;
import org.springframework.stereotype.Component;

@Component
public class TestMapper {
    private final QuestionMapper questionMapper;

    public TestMapper(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    public TestDTO toDto(Test test) {
        return new TestDTO(test.getId(), test.getTitle());
    }

    public Test toTest(TestCreationRequest request) {
        Test test = new Test(request.getName());
        request.getQuestions().forEach(q -> test.addQuestion(questionMapper.toQuestion(q)));
        return test;
    }
}
