package com.spring.reference.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
    final String URL2 = "https://test:Pa$$w0rd@httpbin.org/post";
    final String URL3 = "https://@httpbin.org/post";

    private RestTemplate restTemplate;

    public Map<String, Object> uploadFile(MultipartFile file, HttpHeaders headers){

        FileMetadata fileMetadata = new FileMetadata(file);

        // Create the request body with multipart data
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileMetadata);

        // Set the headers
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Create the request entity with body and headers
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Create parameterized type reference for Map<String, Object>
        ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {};

        // Send the POST request to the server
        ResponseEntity<Map<String, Object>> responseEntity =
                restTemplate.exchange(URL2, HttpMethod.POST,
                        requestEntity,responseType);

        return getResponseBody(responseEntity);
    }

    private static Map<String, Object> getResponseBody(ResponseEntity<Map<String, Object>> responseEntity) {
        Map<String, Object> responseBody = new HashMap<>();
        if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
            responseBody = responseEntity.getBody();
            // Extract and return the response body
            // Assuming the response contains a list of strings under the key "result"
            if (responseBody == null) {
                throw new NullPointerException("Response body is null");
            }
            // Handle the case where the response body is null
        }
        return responseBody;
    }
}
