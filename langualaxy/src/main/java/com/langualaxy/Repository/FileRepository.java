package com.langualaxy.Repository;

import com.langualaxy.Dtos.FileDTO;
import com.langualaxy.Entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

    @Query("select new com.langualaxy.Dtos.FileDTO (f.id, f.fileName) from FileEntity f")
    List<FileDTO> selectAllWithoutData();
}

