package com.springHelloWorld.controller;


import com.springHelloWorld.model.Temperature;
import com.springHelloWorld.service.TemperatureConvertorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class TemperatureController {
    @Autowired
    TemperatureConvertorService temperatureConvertorService;

    @GetMapping("/temperature")
    public List<String> test() {
        return Arrays.asList("Temperature","Testing");
    }

    @PostMapping(path = "/temperature-converter/value")
    public ResponseEntity<Double> convertTemperature(@RequestBody Map<String, Double> value) {
        return ResponseEntity.ok(temperatureConvertorService.convertTemperatureValue(value.get("value")));
    }

    @PostMapping(path = "temperature-converter/values")
    public ResponseEntity<List<Double>> convertTemperatures(@RequestBody Map<String, List<Double>> body) {
        //Extract the List out of the Request body
        List<Double> temperatures = body.get("values");
        return ResponseEntity.ok(temperatureConvertorService.convertTemperatureValues(temperatures));
    }

    @PostMapping(path = "temperature-converter/")
    public ResponseEntity<List<Double>> convertTemperaturesUsingObject(@RequestBody Temperature value) {
        return ResponseEntity.ok(temperatureConvertorService.convertTemperatureValues(value.getValues()));
    }
}
