package com.langualaxy.Request;

import java.util.List;

public class QuestionRequest {
    private String text;
    private List<OptionRequest> options;
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public List<OptionRequest> getOptions() {
        return options;
    }
    public void setOptions(List<OptionRequest> options) {
        this.options = options;
    }
}
