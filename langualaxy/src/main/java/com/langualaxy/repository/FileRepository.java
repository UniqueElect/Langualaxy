package com.langualaxy.repository;

import com.langualaxy.dto.FileDTO;
import com.langualaxy.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

    @Query("select new com.langualaxy.dto.FileDTO (f.id, f.fileName) from FileEntity f")
    List<FileDTO> selectAllWithoutData();
}

