package com.langualaxy.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QuestionCreationRequest(@NotBlank String text, @NotNull Long testId) {

}
