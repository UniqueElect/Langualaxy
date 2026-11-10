package com.langualaxy.Service;

import com.langualaxy.Dtos.FlashCardDTO;
import com.langualaxy.Entity.FlashCard;
import com.langualaxy.Entity.Theme;
import com.langualaxy.Exceptions.NotFoundException;
import com.langualaxy.Mappers.FlashCardMapper;
import com.langualaxy.Repository.FlashCardRepository;
import com.langualaxy.Repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashCardService {

    private final FlashCardRepository flashCardRepository;
    private final ThemeRepository themeRepository;
    private final FlashCardMapper flashCardMapper;
    public FlashCardService(FlashCardRepository flashCardRepository, ThemeRepository themeRepository, FlashCardMapper flashCardMapper){
        this.flashCardRepository = flashCardRepository;
        this.themeRepository = themeRepository;
        this.flashCardMapper = flashCardMapper;
    }

    public FlashCardDTO createFlashCard(String themeText, String word, byte[] image) {
        Theme flashCardTheme = themeRepository.findByText(themeText)
                .orElseGet(() -> themeRepository.save(new Theme(themeText)));
        FlashCard flashCard = new FlashCard();
        flashCard.setTheme(flashCardTheme);
        flashCard.setWord(word);
        flashCard.setImage(image);
        return flashCardMapper.toDto(flashCardRepository.save(flashCard));
    }

    public FlashCardDTO getFlashCardById(Long id) {
        return flashCardRepository.findById(id).map(flashCardMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Flashcard", id));
    }

    public List<FlashCardDTO> getAllFlashcards() {
        return flashCardRepository.findAll().stream().map(flashCardMapper::toDto).toList();
    }

    public List<FlashCardDTO> getFlashcardsByThemeId(Long themeId) {
        return  flashCardRepository.findByTheme_Id(themeId).stream().map(flashCardMapper::toDto).toList();
    }

    public void updateFlashCard(FlashCard flashCard) {
        FlashCard card = flashCardRepository.findById(flashCard.getId()).
                orElseThrow(() -> new NotFoundException("Flashcard", flashCard.getId()));
        card.setWord(flashCard.getWord());
        flashCardRepository.save(card);
    }

    public void deleteFlashCard(Long id) {
        flashCardRepository.deleteById(id);
    }
}
