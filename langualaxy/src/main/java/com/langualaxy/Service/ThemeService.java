package com.langualaxy.Service;

import com.langualaxy.Dtos.ThemeDTO;
import com.langualaxy.Entity.Theme;
import com.langualaxy.Exceptions.NotFoundException;
import com.langualaxy.Mappers.ThemeMapper;
import com.langualaxy.Repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;

    public ThemeService(ThemeRepository themeRepository, ThemeMapper themeMapper){
        this.themeRepository = themeRepository;
        this.themeMapper = themeMapper;
    }

    public ThemeDTO createTheme(String text) {
        Theme theme = new Theme(text);
        return themeMapper.toDto(themeRepository.save(theme));
    }
    public ThemeDTO getThemeById(Long id) {
        return themeRepository.findById(id).map(themeMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Theme", id));
    }
    public List<ThemeDTO> getAllThemes() {
        return themeRepository.findAll().stream().map(themeMapper::toDto).toList();
    }
    public void updateTheme(Theme theme) {
        Theme th =  themeRepository.findById(theme.getId()).orElseThrow(() -> new NotFoundException("Theme", theme.getId()));
        th.setText(theme.getText());
        themeRepository.save(th);
    }
    public void deleteTheme(Long id) {
        themeRepository.deleteById(id);
    }
}
