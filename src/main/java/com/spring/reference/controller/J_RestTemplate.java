package com.spring.reference.controller;

import com.spring.reference.service.FileUploadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.spring.reference.dto.WordResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
public class J_RestTemplate {

    private RestTemplate restTemplate;
    private FileUploadService fileUploadService;
    final String DATA_MUSE_URL_NEEDING_URI_VARIABLES = "https://api.datamuse.com/words?ml={word}&max={max}";

  @PostMapping("/payment/upload")
  public ResponseEntity<Map<String, Object>> testHttpBin(
      @RequestParam("file") MultipartFile file, @RequestHeader HttpHeaders headers) {
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

    @PostMapping("/getWords")
    public List<String> getData(@RequestBody Map<String,Object> requestBody){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("word", (String) requestBody.get("word"));
        uriVariables.put("max", (String) requestBody.get("max"));

        ResponseEntity<WordResponse[]> getForEntity = getForEntity(uriVariables);

        ResponseEntity<WordResponse[]> response = getExchange(requestBody, uriVariables );

        //RestResponse responseNEW = response.getBody();
        log.info("==== RESTful API Response using Spring RESTTemplate START =======");

        List<String> wordList = new ArrayList<>();
        for (WordResponse x : response.getBody()) {
            wordList.add(x.getWord());
        }
        log.info("==== RESTful API Response using Spring RESTTemplate END =======");

        return wordList;
    }

    private ResponseEntity<WordResponse[]> getExchange(Map<String, Object> requestBody, Map<String, String> uriVariables ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ParameterizedTypeReference<WordResponse[]> responseType = new ParameterizedTypeReference<WordResponse[]>() {};
        ResponseEntity<WordResponse[]> response = restTemplate.exchange(DATA_MUSE_URL_NEEDING_URI_VARIABLES, HttpMethod.GET,
                null, responseType, uriVariables);//RequestEntity/body is not needed, but the path params in the form of uriVariables
        return response;
    }

    private ResponseEntity<WordResponse[]> getForEntity(Map<String, String> uriVariables ) {
        ResponseEntity<WordResponse[]> response = restTemplate.getForEntity(DATA_MUSE_URL_NEEDING_URI_VARIABLES, WordResponse[].class, uriVariables);
       // ResponseEntity<WordResponse[]> response = restTemplate.getForEntity(DATA_MUSE_URL_NEEDING_URI_VARIABLES, WordResponse[].class, uriVariables);
        return response;
    }
}
