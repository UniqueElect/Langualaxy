package com.langualaxy.Controller;

import com.langualaxy.Entity.FileEntity;
import com.langualaxy.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileService.saveFile(file);
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + file.getOriginalFilename());
        }
    }
    @GetMapping("/all")
    public List<FileEntity> getAllFiles() {
        return fileService.getAllFiles();
    }
    @PostMapping("/rename/{id}")
    public ResponseEntity<String> renameFile(@PathVariable Long id, @RequestBody String newFileName) {
        try {
            fileService.renameFile(id, newFileName);
            return ResponseEntity.ok("File name updated successfully");
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while renaming the file");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<FileEntity> fileOptional = fileService.getFile(id);
        if (fileOptional.isPresent()) {
            FileEntity file = fileOptional.get();
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + file.getFileName() + "\"")
                    .body(file.getData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("delete/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        fileService.deleteFile(id);
        return ResponseEntity.status(HttpStatus.OK).body("File deleted successfully");
    }
}
