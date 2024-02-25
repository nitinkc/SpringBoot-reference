package com.springHelloWorld.controller;

import com.springHelloWorld.service.qualifer.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService creditCardpaymentService;
    private PaymentService onlineBankingpaymentService;

    @Autowired
    public PaymentController(@Qualifier(value = "creditCardService") PaymentService creditCardPaymentService,
                             @Qualifier("onlineBankingService") PaymentService onlineBankingpaymentService) {
        this.creditCardpaymentService = creditCardPaymentService;
        this.onlineBankingpaymentService = onlineBankingpaymentService;
    }

    @GetMapping(value = "/creditCard",
            produces = { "application/json"})
    public String getPaymentWithCC(){
        return creditCardpaymentService.processPayment();
    }

    @GetMapping(value = "/onlineBanking",
            produces = { "application/json"})
    public String getPaymentWithOB(){
        return onlineBankingpaymentService.processPayment();
    }
}