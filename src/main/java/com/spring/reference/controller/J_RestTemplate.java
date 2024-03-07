package com.spring.reference.controller;

import com.spring.reference.service.FileUploadService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class J_RestTemplate {

    FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> testHttpBin(@RequestParam("file") MultipartFile file,
                                                           @RequestHeader HttpHeaders headers) {
        // Handle file upload here
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error Message","Please upload a file."));
        }

        String fileName = file.getOriginalFilename();
        Map<String,Object> response = fileUploadService.uploadFile(file, headers);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
