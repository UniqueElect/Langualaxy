package com.langualaxy.Mappers;

import com.langualaxy.Dtos.TestOptionDTO;
import com.langualaxy.Entity.TestOption;
import com.langualaxy.Request.OptionRequest;
import org.springframework.stereotype.Component;

@Component
public class TestOptionMapper {
    public TestOptionDTO toDTO(TestOption option) {
        return new TestOptionDTO(option.getId(), option.getText());
    }

    public TestOption toTestOption(OptionRequest request) {
        return new TestOption(request.getText(), request.isCorrect());
    }
}
