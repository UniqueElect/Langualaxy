package com.langualaxy.mapper;

import com.langualaxy.dto.FlashCardDTO;
import com.langualaxy.entity.FlashCard;
import org.springframework.stereotype.Component;

@Component
public class FlashCardMapper {
    public FlashCardDTO toDto(FlashCard flashCard){
        return new FlashCardDTO(flashCard.getId(), flashCard.getImage(), flashCard.getWord());
    }
}
