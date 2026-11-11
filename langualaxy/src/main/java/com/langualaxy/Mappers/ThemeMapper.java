package com.langualaxy.Mappers;

import com.langualaxy.Dtos.ThemeDTO;
import com.langualaxy.Entity.Theme;
import org.springframework.stereotype.Component;

@Component
public class ThemeMapper {
    public ThemeDTO toDto(Theme theme){
        return new ThemeDTO(theme.getId(), theme.getText());
    }
}
