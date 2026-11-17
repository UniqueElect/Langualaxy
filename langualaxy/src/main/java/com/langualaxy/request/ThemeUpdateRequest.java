package com.langualaxy.request;

import jakarta.validation.constraints.NotBlank;

public record ThemeUpdateRequest(@NotBlank String text) {
}
