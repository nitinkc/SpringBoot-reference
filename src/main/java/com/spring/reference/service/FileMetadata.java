package com.spring.reference.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileMetadata {
    private String filename;
    private String contentType;
    // Add other relevant properties if needed

    public FileMetadata(MultipartFile file) {
        this.filename = file.getOriginalFilename();
        this.contentType = file.getContentType();
        // Initialize other properties as needed
    }

}