package com.langualaxy.Mappers;

import com.langualaxy.Dtos.QuestionDTO;
import com.langualaxy.Entity.Question;
import org.springframework.stereotype.Component;

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
}
