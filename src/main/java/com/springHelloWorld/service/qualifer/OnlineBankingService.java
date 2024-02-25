package com.springHelloWorld.service.qualifer;

import org.springframework.stereotype.Service;

@Service("onlineBankingService")
public class OnlineBankingService implements PaymentService{
    @Override
    public String processPayment() {
        return "Paid via Online Banking";
    }
}
