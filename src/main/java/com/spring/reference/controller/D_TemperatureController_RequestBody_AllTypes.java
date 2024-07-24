package com.spring.reference.controller;

import com.spring.reference.model.Temperature;
import com.spring.reference.service.TemperatureConvertorService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class D_TemperatureController_RequestBody_AllTypes {
    @Autowired
    TemperatureConvertorService temperatureConvertorService;

    @GetMapping("/temperature")
    public List<String> test() {
        return List.of("Temperature","Testing");
    }

    @PostMapping(path = "/temperature-converter/value")
    public ResponseEntity<Double> convertTemperature(@RequestBody Map<String, Double> value) {
        return ResponseEntity.ok(temperatureConvertorService.convertTemperatureValue(value.get("value")));
    }

    @PostMapping(path = "temperature-converter/values")
    public ResponseEntity<List<Double>> convertTemperatures(@RequestBody Map<String, List<Double>> body) {
        List<Double> result = body.get("values").stream()
                .map(temperatureConvertorService::convertTemperatureValue)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "temperature-converter/")
    public ResponseEntity<List<Double>> convertTemperaturesUsingObject(@RequestBody Temperature value) {
        return ResponseEntity.ok(temperatureConvertorService.convertTemperatureValues(value));
    }

    @GetMapping("/")
    public ResponseEntity<String> getLocal(){
        // Constructing the link URL
        String linkUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/actuator")
                .toUriString();

        // Creating a clickable link in the response
        String responseMessage = "Click <a href=\"" + linkUrl + "\">http://localhost:8090/actuator</a> to access the resource.";

        // ResponseEntity with a status code and body containing the clickable link
        return ResponseEntity.ok(responseMessage);
    }
}
