package com.langualaxy.mapper;

import com.langualaxy.dto.TestOptionDTO;
import com.langualaxy.entity.TestOption;
import com.langualaxy.request.OptionRequest;
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
