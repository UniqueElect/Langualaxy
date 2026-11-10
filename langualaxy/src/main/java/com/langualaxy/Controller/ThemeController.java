package com.langualaxy.Controller;

import com.langualaxy.Dtos.ThemeDTO;
import com.langualaxy.Entity.Theme;
import com.langualaxy.Service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/themes")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @PostMapping
    public ThemeDTO createTheme(@RequestBody Theme theme) {
        return themeService.createTheme(theme.getText());
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
    public void updateTheme(@PathVariable Long id, @RequestBody Theme theme) {
        theme.setId(id);
        themeService.updateTheme(theme);
    }
    @GetMapping("/delete/{id}")
    public void deleteTheme(@PathVariable Long id) {
        themeService.deleteTheme(id);
    }
}
