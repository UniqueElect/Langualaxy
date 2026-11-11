package com.langualaxy.Service;

import com.langualaxy.Dtos.ThemeDTO;
import com.langualaxy.Entity.Theme;
import com.langualaxy.Exceptions.NotFoundException;
import com.langualaxy.Mappers.ThemeMapper;
import com.langualaxy.Repository.ThemeRepository;
import com.langualaxy.Request.ThemeCreationRequest;
import com.langualaxy.Request.ThemeUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;

    public ThemeService(ThemeRepository themeRepository, ThemeMapper themeMapper){
        this.themeRepository = themeRepository;
        this.themeMapper = themeMapper;
    }
    @Transactional
    public ThemeDTO createTheme(ThemeCreationRequest request) {
        Theme theme = new Theme(request.text());
        return themeMapper.toDto(themeRepository.save(theme));
    }
    @Transactional(readOnly = true)
    public ThemeDTO getThemeById(Long id) {
        return themeRepository.findById(id).map(themeMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Theme", id));
    }
    @Transactional(readOnly = true)
    public List<ThemeDTO> getAllThemes() {
        return themeRepository.findAll().stream().map(themeMapper::toDto).toList();
    }
    @Transactional
    public void updateTheme(Long id, ThemeUpdateRequest request) {
        Theme th =  themeRepository.findById(id).orElseThrow(() -> new NotFoundException("Theme", id));
        th.setText(request.text());
        themeRepository.save(th);
    }
    @Transactional
    public void deleteTheme(Long id) {
        themeRepository.deleteById(id);
    }
}
