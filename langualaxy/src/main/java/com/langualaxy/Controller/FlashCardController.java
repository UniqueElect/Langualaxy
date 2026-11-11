package com.langualaxy.Controller;

import com.langualaxy.Dtos.FlashCardDTO;
import com.langualaxy.Request.FlashCardUpdateRequest;
import com.langualaxy.Service.FlashCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/flashcards")
public class FlashCardController {
    private final FlashCardService flashCardService;

    public FlashCardController(FlashCardService flashCardService) {
        this.flashCardService = flashCardService;
    }

    @PostMapping("/create")
    public ResponseEntity<FlashCardDTO> createFlashCard(
            @RequestParam("theme") String themeText,
            @RequestParam("word") String word,
            @RequestParam("image") MultipartFile image) throws IOException {
        byte[] imageBytes = null;
        imageBytes = image.getBytes();
        FlashCardDTO flashCard = flashCardService.createFlashCard(themeText, word, imageBytes);
        return new ResponseEntity<>(flashCard, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public FlashCardDTO getFlashCardById(@PathVariable Long id) {
        return flashCardService.getFlashCardById(id);
    }

    @GetMapping("/all")
    public List<FlashCardDTO> getAllFlashcards() {
        return flashCardService.getAllFlashcards();
    }

    @GetMapping("/get/{themeId}")
    public List<FlashCardDTO> getFlashcardsByThemeId(@PathVariable Long themeId) {
        return flashCardService.getFlashcardsByThemeId(themeId);
    }

    @PostMapping("edit/{id}")
    public FlashCardDTO updateFlashCard(@PathVariable Long id, @RequestBody FlashCardUpdateRequest request) {
        return flashCardService.updateFlashCard(id, request);
    }

    @GetMapping("/delete/{id}")
    public void deleteFlashCard(@PathVariable Long id) {
        flashCardService.deleteFlashCard(id);
    }
}
