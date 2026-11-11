package com.langualaxy.Controller;

import com.langualaxy.Dtos.FileDTO;
import com.langualaxy.Entity.FileEntity;
import com.langualaxy.Service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

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
    public List<FileDTO> getAllFiles() {
        return fileService.getAllFiles();
    }

    @PostMapping("/rename/{id}")
    public ResponseEntity<String> renameFile(@PathVariable Long id, @RequestBody String newFileName) {
        fileService.renameFile(id, newFileName);
        return ResponseEntity.ok("File name updated successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        FileEntity file = fileService.getFile(id);
        return ResponseEntity.ok()
                .header("Content-Disposition",
                        "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file.getData());
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        fileService.deleteFile(id);
        return ResponseEntity.status(HttpStatus.OK).body("File deleted successfully");
    }
}
