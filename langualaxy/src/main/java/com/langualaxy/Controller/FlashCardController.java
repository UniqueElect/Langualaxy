package com.langualaxy.Controller;

import com.langualaxy.Entity.FlashCard;
import com.langualaxy.Entity.Theme;
import com.langualaxy.Service.FlashCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/flashcards")
public class FlashCardController {

    @Autowired
    private FlashCardService flashCardService;

    @PostMapping("/create")
    public ResponseEntity<String> createFlashCard(
            @RequestParam("theme") String themeText,
            @RequestParam("word") String word,
            @RequestParam("image") MultipartFile image) {
        Theme theme = new Theme();
        theme.setText(themeText);
        byte[] imageBytes = null;
        if (!image.isEmpty()) {
            try {
                imageBytes = image.getBytes();
            } catch (IOException e) {
                return ResponseEntity.badRequest().body("Failed to load the image: " + e.getMessage());
            }
        }
        FlashCard flashCard = flashCardService.createFlashCard(theme, word, imageBytes);
        return ResponseEntity.ok("norm");
    }
    @GetMapping("/{id}")
    public FlashCard getFlashCardById(@PathVariable Long id) {
        return flashCardService.getFlashCardById(id);
    }
    @GetMapping("/all")
    public List<FlashCard> getAllFlashcards() {
        return flashCardService.getAllFlashcards();
    }
    @GetMapping("/get/{themeId}")
    public List<FlashCard> getFlashcardsByThemeId(@PathVariable Long themeId) {
        return flashCardService.getFlashcardsByThemeId(themeId);
    }
    @PostMapping("edit/{id}")
    public void updateFlashCard(@PathVariable Long id, @RequestBody FlashCard flashCard) {
        flashCard.setId(id);
        flashCardService.updateFlashCard(flashCard);
    }
    @GetMapping("/delete/{id}")
    public void deleteFlashCard(@PathVariable Long id) {
        flashCardService.deleteFlashCard(id);
    }
}
