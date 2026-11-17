package com.langualaxy.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class TestCreationRequest {
    @NotBlank
    private String name;
    @NotEmpty
    @Valid
    private List<QuestionRequest> questions;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<QuestionRequest> getQuestions() {
        return questions;
    }
    public void setQuestions(List<QuestionRequest> questions) {
        this.questions = questions;
    }
}
