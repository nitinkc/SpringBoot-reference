package com.spring.reference.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CurrencyConvertorServiceTest {

    private CurrencyConvertorService currencyConvertorService;

    @BeforeEach
    public void setUp() {
        currencyConvertorService = new CurrencyConvertorService();
    }

  @Test
  void testConvertCurrencies_withMixedValues_returnsCorrectConvertedValues() {
        List<Double> currencies = Arrays.asList(10.0, 20.0, 30.0, 40.0);
        List<Double> expectedResult = Arrays.asList(723.4, 1446.8, 2170.2, 2893.6);

        List<Double> result = currencyConvertorService.convertCurrencies(currencies);

        assertEquals(expectedResult, result);
    }

  @Test
  void testConvertCurrencies_withEmptyList_returnsEmptyList() {
        List<Double> currencies = Arrays.asList();
        List<Double> expectedResult = Arrays.asList();

        List<Double> result = currencyConvertorService.convertCurrencies(currencies);

        assertEquals(expectedResult, result);
    }

    @Test
    void testConvertCurrencies_withNullList_throwsNullPointerException() {
        List<Double> currencies = null;

        assertThrows(NullPointerException.class, () -> currencyConvertorService.convertCurrencies(currencies));
    }

  @Test
  void testConvertCurrencies_withNegativeValues_returnsCorrectConvertedValues() {
        List<Double> currencies = Arrays.asList(-10.0, -20.0, 30.0, -40.0);
        List<Double> expectedResult = Arrays.asList(-723.4, -1446.8, 2170.2, -2893.6);

        List<Double> result = currencyConvertorService.convertCurrencies(currencies);

        assertEquals(expectedResult, result);
    }

  @Test
  void testConvertCurrencies_withZeroValues_returnsCorrectConvertedValues() {
        List<Double> currencies = Arrays.asList(0.0, 0.0, 0.0);
        List<Double> expectedResult = Arrays.asList(0.0, 0.0, 0.0);

        List<Double> result = currencyConvertorService.convertCurrencies(currencies);

        assertEquals(expectedResult, result);
    }
}