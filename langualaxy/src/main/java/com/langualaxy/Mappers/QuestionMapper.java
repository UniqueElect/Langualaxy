package com.langualaxy.Mappers;

import com.langualaxy.Dtos.QuestionDTO;
import com.langualaxy.Entity.Question;
import com.langualaxy.Entity.TestOption;
import com.langualaxy.Request.QuestionRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionMapper {
    private final TestOptionMapper testOptionMapper;

    public QuestionMapper(TestOptionMapper testOptionMapper) {
        this.testOptionMapper = testOptionMapper;
    }

    public QuestionDTO toDTO(Question question) {
        return new QuestionDTO(question.getId(), question.getText(),
                question.getOptions().stream()
                        .map(testOptionMapper::toDTO).toList()
        );
    }

    public QuestionDTO toDTO(Question question, List<TestOption> options) {
        return new QuestionDTO(question.getId(), question.getText(),
                options.stream().map(testOptionMapper::toDTO).toList());
    }

    public Question toQuestion(QuestionRequest request) {
        Question question = new Question(request.getText());
        request.getOptions().forEach(o -> question.addOption(testOptionMapper.toTestOption(o)));
        return question;
    }
}
