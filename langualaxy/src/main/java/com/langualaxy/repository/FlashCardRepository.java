package com.langualaxy.repository;

import com.langualaxy.entity.FlashCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashCardRepository extends JpaRepository<FlashCard, Long> {
    List<FlashCard> findByTheme_Id(Long themeId);
}
