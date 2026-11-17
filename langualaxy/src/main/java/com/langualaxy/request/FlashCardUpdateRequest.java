package com.langualaxy.request;

import jakarta.validation.constraints.NotNull;

public record FlashCardUpdateRequest(@NotNull String word) {
}
