package com.spring.reference.controller;


import com.spring.reference.model.Currency;
import com.spring.reference.service.CurrencyConvertorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class C_CurrencyController_PathVsReqParam {
    @Autowired
    CurrencyConvertorService currencyConvertorService;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public Double convertCurrency(@PathVariable String from,
                                  @PathVariable String to,
                                  @PathVariable Double quantity) {

        //Calling the Service Method
         Double convertedValue = currencyConvertorService.convertCurrency(from,to,quantity);

        return convertedValue;
    }

    @GetMapping("/currency-converter")
    public Double testEmail(@RequestParam(value = "from") String from,
                            @RequestParam(value = "to", required = true, defaultValue = "INR") String to,
                            @RequestParam(value = "quantity", required = false, defaultValue = "10.00") Double quantity) {

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