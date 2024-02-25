package com.springHelloWorld.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Validated
public class CValidationController {

    @GetMapping("/email")
    public String testEmail(//@Valid @Email(message = "Please provide a valid email address")
                            @RequestParam(value = "email") String email ,
                            @RequestParam(value = "greet", required = false, defaultValue = "No Val from Request") String greet,
                            @RequestParam(value = "count", required = false, defaultValue = "-1") Integer count) {

        StringBuilder sb= new StringBuilder();
        sb.append(email).append(" email OK").append("\nCount is ").append(count).append("\n").append(greet);
        return sb.toString();
    }

    @GetMapping(path = "/pathVariable/{var_name}")
    public String helloWorldPathVariable(@PathVariable("var_name") String name) {
        return String.format("The Value returned is %s", name);
    }
}