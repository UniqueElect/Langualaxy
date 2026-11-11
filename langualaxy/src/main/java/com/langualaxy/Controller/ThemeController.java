package com.langualaxy.Controller;

import com.langualaxy.Dtos.ThemeDTO;
import com.langualaxy.Request.ThemeCreationRequest;
import com.langualaxy.Request.ThemeUpdateRequest;
import com.langualaxy.Service.ThemeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/themes")
public class ThemeController {

    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @PostMapping
    public ThemeDTO createTheme(@RequestBody ThemeCreationRequest request) {
        return themeService.createTheme(request);
    }

    @GetMapping("/{id}")
    public ThemeDTO getThemeById(@PathVariable Long id) {
        return themeService.getThemeById(id);
    }

    @GetMapping("/all")
    public List<ThemeDTO> getAllThemes() {
        return themeService.getAllThemes();
    }

    @PostMapping("/edit/{id}")
    public void updateTheme(@PathVariable Long id, @RequestBody ThemeUpdateRequest request) {
        themeService.updateTheme(id, request);
    }

    @GetMapping("/delete/{id}")
    public void deleteTheme(@PathVariable Long id) {
        themeService.deleteTheme(id);
    }
}
