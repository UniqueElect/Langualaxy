package com.langualaxy.Request;

import java.util.List;

public class TestCreationRequest {
    private String name;
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
