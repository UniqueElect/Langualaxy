package com.langualaxy.Repository;

import com.langualaxy.Entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Theme findByText(String text);
}
