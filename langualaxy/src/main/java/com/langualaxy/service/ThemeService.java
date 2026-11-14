package com.langualaxy.service;

import com.langualaxy.dto.ThemeDTO;
import com.langualaxy.entity.Theme;
import com.langualaxy.exception.NotFoundException;
import com.langualaxy.mapper.ThemeMapper;
import com.langualaxy.repository.ThemeRepository;
import com.langualaxy.request.ThemeCreationRequest;
import com.langualaxy.request.ThemeUpdateRequest;
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
    public ThemeDTO updateTheme(Long id, ThemeUpdateRequest request) {
        Theme th =  themeRepository.findById(id).orElseThrow(() -> new NotFoundException("Theme", id));
        th.setText(request.text());
        return themeMapper.toDto(themeRepository.save(th));
    }
    @Transactional
    public void deleteTheme(Long id) {
        themeRepository.deleteById(id);
    }
}
