package com.langualaxy.Dtos;


import java.util.List;

public record QuestionDTO(Long id, String text, List<TestOptionDTO> options) {
}
