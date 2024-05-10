package com.langualaxy.Repository;

import com.langualaxy.Entity.TestOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestOptionRepository extends JpaRepository<TestOption, Long> {
    List<TestOption> findByQuestionId(Long questionId);
}
