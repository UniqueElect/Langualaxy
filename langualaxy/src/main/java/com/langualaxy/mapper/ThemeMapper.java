package com.langualaxy.mapper;

import com.langualaxy.dto.ThemeDTO;
import com.langualaxy.entity.Theme;
import org.springframework.stereotype.Component;

@Component
public class ThemeMapper {
    public ThemeDTO toDto(Theme theme){
        return new ThemeDTO(theme.getId(), theme.getText());
    }
}
