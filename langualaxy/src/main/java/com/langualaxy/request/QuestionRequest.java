package com.langualaxy.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class QuestionRequest {
    @NotBlank
    private String text;
    @Size(min = 2)
    @NotNull
    @Valid
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
