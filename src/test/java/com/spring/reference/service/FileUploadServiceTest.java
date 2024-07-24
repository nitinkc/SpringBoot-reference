package com.spring.reference.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Disabled
class FileUploadServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FileUploadService fileUploadService;

    private HttpHeaders headers;

    @BeforeEach
    public void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    }

    @Test
    void testUploadFile_Success() throws IOException {
        // Given
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "Hello, World!".getBytes());
        Map<String, Object> expectedResponseBody = new HashMap<>();
        expectedResponseBody.put("status", "success");

        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(expectedResponseBody, HttpStatus.OK);
        when(restTemplate.exchange(eq("https://test:Pa$$w0rd@httpbin.org/post"), eq(HttpMethod.POST), any(HttpEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(responseEntity);

        // When
        Map<String, Object> responseBody = fileUploadService.uploadFile(multipartFile, headers);

        // Then
        assertNotNull(responseBody);
        assertEquals(expectedResponseBody, responseBody);
    }

    @Test
    void testUploadFile_Failure() throws IOException {
        // Given
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "Hello, World!".getBytes());
        Map<String, Object> expectedResponseBody = new HashMap<>();
        expectedResponseBody.put("status", "error");

        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(expectedResponseBody, HttpStatus.INTERNAL_SERVER_ERROR);
        when(restTemplate.exchange(eq("https://test:Pa$$w0rd@httpbin.org/post"), eq(HttpMethod.POST), any(HttpEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(responseEntity);

        // When
        Map<String, Object> responseBody = fileUploadService.uploadFile(multipartFile, headers);

        // Then
        assertNotNull(responseBody);
        assertEquals(expectedResponseBody, responseBody);
    }

    @Test
    void testUploadFile_NullResponse() throws IOException {
        // Given
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "Hello, World!".getBytes());

        when(restTemplate.exchange(eq("https://test:Pa$$w0rd@httpbin.org/post"), eq(HttpMethod.POST), any(HttpEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(null);

        // When
        Map<String, Object> responseBody = fileUploadService.uploadFile(multipartFile, headers);

        // Then
        assertNull(responseBody);
    }

    @Test
    void testUploadFile_EmptyResponse() {
        // Given
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "".getBytes());
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(HttpStatus.OK);

        when(restTemplate.exchange(
                eq("https://test:Pa$$w0rd@httpbin.org/post"),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                any(ParameterizedTypeReference.class)
        )).thenReturn(responseEntity);

        // When
        Map<String, Object> responseBody = fileUploadService.uploadFile(multipartFile, headers);

        // Then
        assertNull(responseBody);
    }

    @Test
    void testUploadFile_InvalidUrl() throws IOException {
        // Given
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "Hello, World!".getBytes());
        String invalidUrl = "https://invalid_url.com";

        // When
        try {
            fileUploadService.uploadFile(multipartFile, headers);
        } catch (Exception e) {
            // Then
            assertEquals(NullPointerException.class, e.getClass());
        }
    }
}
