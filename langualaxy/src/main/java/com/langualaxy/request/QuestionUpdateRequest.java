package com.langualaxy.request;

import jakarta.validation.constraints.NotBlank;

public record QuestionUpdateRequest(@NotBlank String text) {
}
