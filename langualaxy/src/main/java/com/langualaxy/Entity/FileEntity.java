package com.langualaxy.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    @Lob
    @Column(name = "data", columnDefinition = "LONGBLOB")
    private byte[] data;
    public String getFileName() {
        return fileName;
    }
    public Long getId() {
        return id;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
}
