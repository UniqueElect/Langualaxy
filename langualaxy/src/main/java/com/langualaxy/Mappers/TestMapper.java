package com.langualaxy.Mappers;

import com.langualaxy.Dtos.TestDTO;
import com.langualaxy.Entity.Test;
import com.langualaxy.Request.TestCreationRequest;
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
