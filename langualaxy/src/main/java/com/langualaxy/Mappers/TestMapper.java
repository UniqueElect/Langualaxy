package com.langualaxy.Mappers;

import com.langualaxy.Dtos.TestDTO;
import com.langualaxy.Entity.Test;
import org.springframework.stereotype.Component;

@Component
public class TestMapper {
    public TestDTO toDto(Test test){
        return new TestDTO(test.getId(), test.getTitle());
    }
}
