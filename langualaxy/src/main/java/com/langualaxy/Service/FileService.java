package com.langualaxy.Service;

import com.langualaxy.Dtos.FileDTO;
import com.langualaxy.Entity.FileEntity;
import com.langualaxy.Exceptions.NotFoundException;
import com.langualaxy.Repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    @Transactional
    public void saveFile(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setData(file.getBytes());
        fileRepository.save(fileEntity);
    }
    @Transactional(readOnly = true)
    public FileEntity getFile(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("File", id));
    }

    @Transactional
    public void renameFile(Long id, String newFileName) throws FileNotFoundException {
        FileEntity file = fileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("File", id));
        file.setFileName(newFileName);
        fileRepository.save(file);
    }
    @Transactional(readOnly = true)
    public List<FileDTO> getAllFiles() {
        return fileRepository.selectAllWithoutData();
    }
    @Transactional
    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }
}
