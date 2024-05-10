package com.langualaxy.Service;

import com.langualaxy.Entity.FlashCard;
import com.langualaxy.Entity.Theme;
import com.langualaxy.Repository.FlashCardRepository;
import com.langualaxy.Repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashCardService {

    @Autowired
    private FlashCardRepository flashCardRepository;
    @Autowired
    private ThemeRepository themeRepository;

    public FlashCard createFlashCard(Theme theme, String word, byte[] image) {
        Theme existingTheme = themeRepository.findByText(theme.getText());
        FlashCard flashCard = new FlashCard();
        if (existingTheme != null) {
            flashCard.setTheme(existingTheme);
        } else {
            existingTheme = new Theme();
            existingTheme.setText(theme.getText());
            existingTheme = themeRepository.save(existingTheme);
            flashCard.setTheme(existingTheme);
        }
        flashCard.setWord(word);
        flashCard.setImage(image);
        return flashCardRepository.save(flashCard);
    }

    public FlashCard getFlashCardById(Long id) {
        return flashCardRepository.findById(id).orElse(null);
    }
    public List<FlashCard> getAllFlashcards() {
        return flashCardRepository.findAll();
    }
    public List<FlashCard> getFlashcardsByThemeId(Long themeId) {
        return flashCardRepository.findByTheme_Id(themeId);
    }
    public void updateFlashCard(FlashCard flashCard) {
        flashCardRepository.save(flashCard);
    }
    public void deleteFlashCard(Long id) {
        flashCardRepository.deleteById(id);
    }
}
