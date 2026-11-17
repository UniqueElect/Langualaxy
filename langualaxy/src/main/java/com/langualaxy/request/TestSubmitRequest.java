package com.langualaxy.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class TestSubmitRequest {
    // key - question id , value - option ids
    @NotNull
    private Map<Long, Set<Long>> answers;
}
