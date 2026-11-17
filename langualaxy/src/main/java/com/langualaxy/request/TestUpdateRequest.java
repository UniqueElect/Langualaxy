package com.langualaxy.request;

import jakarta.validation.constraints.NotBlank;

public record TestUpdateRequest(@NotBlank String title) {
}
