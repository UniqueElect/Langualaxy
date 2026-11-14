package com.langualaxy.mapper;

import com.langualaxy.dto.QuestionDTO;
import com.langualaxy.entity.Question;
import com.langualaxy.entity.TestOption;
import com.langualaxy.request.QuestionRequest;
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
