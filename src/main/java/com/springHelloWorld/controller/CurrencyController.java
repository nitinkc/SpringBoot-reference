package com.springHelloWorld.controller;


import com.springHelloWorld.model.Currency;
import com.springHelloWorld.service.CurrencyConvertorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class CurrencyController {
    @Autowired
    CurrencyConvertorService currencyConvertorService;

    @GetMapping("/")
    public List<String> test() {
        return Arrays.asList("Hello-World!","test");
    }

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public Double convertCurrency(@PathVariable String from,
                                  @PathVariable String to,
                                  @PathVariable Double quantity) {

        //Calling the Service Method
         Double convertedValue = currencyConvertorService.convertCurrency(from,to,quantity);

        return convertedValue;
    }

    @PostMapping(path = "currency-converter/value")
    public ResponseEntity<Double> convertCurrency(@RequestBody Map<String, Double> value) {
        return ResponseEntity.ok(currencyConvertorService.convertCurrencyValue(value.get("value")));
    }

    @PostMapping(path = "currency-converter/values")
    public ResponseEntity<List<Double>> convertCurrencies(@RequestBody Map<String, List<Double>> body) {
        //Extract the List out of the Request body
        List<Double> currencies = body.get("values");
        return ResponseEntity.ok(currencyConvertorService.convertCurrencies(currencies));
    }

    @PostMapping(path = "currency-converter/")
    public ResponseEntity<List<Double>> convertCurrenciesUsingObject(@RequestBody Currency value) {
        return ResponseEntity.ok(currencyConvertorService.convertCurrenciesObject(value.getValues()));
    }
}