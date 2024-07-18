package com.spring.reference.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConvertorService {

     static final double CONVERSION_FACTOR = 72.34;

    public Double convertCurrency(String from, String to, Double quantity){
        if(from.equalsIgnoreCase("USD") && to.equalsIgnoreCase("INR")){
           return getCurrencyRoundedTo2Digits(CONVERSION_FACTOR, quantity);
        }else if(from.equalsIgnoreCase("INR") && to.equalsIgnoreCase("USD")){
            return getCurrencyRoundedTo2Digits((1 / CONVERSION_FACTOR), quantity);
        }

        return -1.00;
    }

    private static double getCurrencyRoundedTo2Digits(double conversionFactor, Double quantity) {
        double value = conversionFactor * quantity;

        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(value));
    }

    public List<Double> convertCurrencies(List<Double> currencies) {

    return currencies.stream()
        .map(currency -> getCurrencyRoundedTo2Digits(CONVERSION_FACTOR, currency))
        .toList();
    }

    public Double convertCurrencyValue(Double value) {
        return getCurrencyRoundedTo2Digits(CONVERSION_FACTOR, value);
    }

    public List<Double> convertCurrenciesObject(List<Double> currencies) {

    return currencies.stream()
       .map(currency -> getCurrencyRoundedTo2Digits(CONVERSION_FACTOR, currency))
       .toList();
    }
}
