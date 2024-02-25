package com.springHelloWorld.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyConvertorService {

    final double conversionFactor = 72.34;

    public Double convertCurrency(String from, String to, Double quantity){
        double conversionFactor = 0.0;
        if(from.equalsIgnoreCase("USD") && to.equalsIgnoreCase("INR")){
            conversionFactor = 72.34;
        }

        return conversionFactor*quantity;
    }

    public List<Double> convertCurrencies(List<Double> currencies) {

        List<Double> result =currencies.stream()
                .map(currency -> currency*conversionFactor)
                .collect(Collectors.toList());

        return result;
    }

    public Double convertCurrencyValue(Double value) {
        return value*conversionFactor;
    }

    public List<Double> convertCurrenciesObject(List<Double> currencies) {

        List<Double> result =currencies.stream()
                .map(currency -> currency*conversionFactor)
                .collect(Collectors.toList());

        return result;
    }
}
