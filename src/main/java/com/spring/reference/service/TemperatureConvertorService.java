package com.spring.reference.service;
import com.spring.reference.model.Temperature;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// C = (5/9) * (F-32)
@Service
public class TemperatureConvertorService {


    private Double tempInCelcius(double F) {
        return (5.0 / 9.0) * (F - 32.0);
    }

    private double tempInFahrenheit(double C) {
        return (9.0 / 5.0) * C + 32.0;
    }

    public Double convertTemperatureValue(Double value) {
        return tempInCelcius(value);
    }

    public List<Double> convertTemperatureValues(Temperature temperatures) {
        List<Double> result = new ArrayList<>();

        if(temperatures.getFrom().equalsIgnoreCase("F") &&
                temperatures.getTo().equalsIgnoreCase("C")){
            result = temperatures.getValues().stream()
                    .map(this::tempInCelcius)
                    .collect(Collectors.toList());
        }

        if(temperatures.getFrom().equalsIgnoreCase("C") &&
                temperatures.getTo().equalsIgnoreCase("F")){
            result = temperatures.getValues().stream()
                    .map(this::tempInFahrenheit)
                    .collect(Collectors.toList());
        }
        return result;
    }
}