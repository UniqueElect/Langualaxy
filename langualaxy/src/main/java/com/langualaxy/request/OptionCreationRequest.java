package com.langualaxy.request;

public record OptionCreationRequest(String text, boolean correct, Long questionId) {
}
