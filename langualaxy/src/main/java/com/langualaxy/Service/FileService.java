package com.langualaxy.Service;

import com.langualaxy.Entity.FileEntity;
import com.langualaxy.Repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public void saveFile(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setData(file.getBytes());
        fileRepository.save(fileEntity);
    }
    public Optional<FileEntity> getFile(Long id) {
        return fileRepository.findById(id);
    }
    @Transactional
    public void renameFile(Long id, String newFileName) throws FileNotFoundException {
        Optional<FileEntity> optionalFile = fileRepository.findById(id);
        if (optionalFile.isPresent()) {
            FileEntity file = optionalFile.get();
            file.setFileName(newFileName);
            fileRepository.save(file);
        } else {
            throw new FileNotFoundException("File with id " + id + " not found");
        }
    }
    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }
    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }
}
