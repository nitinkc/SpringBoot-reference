package com.spring.reference.controller;

import com.spring.reference.service.qualifer.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class I_PaymentController_Qualifier {

    private final PaymentService creditCardpaymentService;
    private final PaymentService onlineBankingpaymentService;

    @Autowired
    public I_PaymentController_Qualifier(@Qualifier(value = "creditCardService") PaymentService creditCardPaymentService,
                                         @Qualifier("onlineBankingService") PaymentService onlineBankingpaymentService) {
        this.creditCardpaymentService = creditCardPaymentService;
        this.onlineBankingpaymentService = onlineBankingpaymentService;
    }

    @GetMapping(value = "/creditCard",
            produces = { "application/json"})
    public String getPaymentWithCC(){
    System.out.println("testing");
        return creditCardpaymentService.processPayment();
    }

    @GetMapping(value = "/onlineBanking",
            produces = { "application/json"})
    public String getPaymentWithOB(){
        return onlineBankingpaymentService.processPayment();
    }
}