package com.langualaxy.Mappers;

import com.langualaxy.Dtos.TestDTO;
import com.langualaxy.Entity.Test;

public class TestMapper {
    public TestDTO toDto(Test test){
        return new TestDTO(test.getId(), test.getTitle());
    }
}
