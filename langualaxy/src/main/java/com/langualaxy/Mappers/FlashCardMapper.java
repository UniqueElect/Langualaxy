package com.langualaxy.Mappers;

import com.langualaxy.Dtos.FlashCardDTO;
import com.langualaxy.Entity.FlashCard;
import org.springframework.stereotype.Component;

@Component
public class FlashCardMapper {
    public FlashCardDTO toDto(FlashCard flashCard){
        return new FlashCardDTO(flashCard.getId(), flashCard.getImage(), flashCard.getWord());
    }
}
