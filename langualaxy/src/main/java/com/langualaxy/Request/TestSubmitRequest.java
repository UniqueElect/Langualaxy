package com.langualaxy.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class TestSubmitRequest {
    // key - question id , value - option ids
    private Map<Long, Set<Long>> answers;
}
