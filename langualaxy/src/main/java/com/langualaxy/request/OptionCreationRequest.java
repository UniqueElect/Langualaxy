package com.langualaxy.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OptionCreationRequest(@NotBlank String text, @NotNull boolean correct, @NotNull Long questionId) {
}
