package com.langualaxy.Repository;

import com.langualaxy.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByTestId(Long testId);
    @Query("select q from Question q left join fetch q.options")
    List<Question> findAllWithOptions();
}
