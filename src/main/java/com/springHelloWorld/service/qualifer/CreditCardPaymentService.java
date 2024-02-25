package com.springHelloWorld.service.qualifer;

import org.springframework.stereotype.Service;

@Service(value = "creditCardService")
public class CreditCardPaymentService implements PaymentService{
    @Override
    public String processPayment() {
        return "Paid via credit card";
    }
}
