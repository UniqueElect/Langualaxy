package com.langualaxy.Service;

import com.langualaxy.Entity.Theme;
import com.langualaxy.Repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;
    public Theme createTheme(String text) {
        Theme theme = new Theme();
        theme.setText(text);
        return themeRepository.save(theme);
    }
    public Theme getThemeById(Long id) {
        return themeRepository.findById(id).orElse(null);
    }
    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }
    public void updateTheme(Theme theme) {
        themeRepository.save(theme);
    }
    public void deleteTheme(Long id) {
        themeRepository.deleteById(id);
    }
}
