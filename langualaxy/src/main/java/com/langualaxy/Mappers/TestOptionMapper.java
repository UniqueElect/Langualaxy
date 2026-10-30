package com.langualaxy.Mappers;

import com.langualaxy.Dtos.TestOptionDTO;
import com.langualaxy.Entity.TestOption;
import org.springframework.stereotype.Component;

@Component
public class TestOptionMapper {
    public TestOptionDTO toDTO(TestOption option) {
        return new TestOptionDTO(option.getId(), option.getText());
    }
}
