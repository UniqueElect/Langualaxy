package com.langualaxy.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OptionUpdateRequest(@NotBlank String text, @NotNull boolean correct) {
}
