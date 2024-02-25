package com.springHelloWorld.service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// C = (5/9) * (F-32)
@Service
public class TemperatureConvertorService {


    private Double tempInCelcius(double F) {
        return (5.0 / 9.0) * (F - 32.0);
    }

    public Double convertTemperatureValue(Double value) {
        return tempInCelcius(value);
    }

    public List<Double> convertTemperatureValues(List<Double> temperatures) {
        return temperatures.stream()
                .map(this::tempInCelcius)
                .collect(Collectors.toList());
    }
}