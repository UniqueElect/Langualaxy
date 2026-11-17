package com.langualaxy.request;

import jakarta.validation.constraints.NotBlank;

public record ThemeCreationRequest(@NotBlank String text) {
}
