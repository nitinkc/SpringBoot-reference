package com.spring.reference.controller;

import com.spring.reference.dto.ExampleRequest;
import com.spring.reference.dto.ExampleResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import java.time.ZonedDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Validated
public class E_ValidationController {

    @GetMapping("/email")
    public String testEmail(@Valid @Email(message = "Please provide a valid email address")
                            @RequestParam(value = "email") String email,
                            @RequestParam(value = "greet", required = false, defaultValue = "No Val from Request") String greet,
                            @RequestParam(value = "count", required = false, defaultValue = "-1") Integer count) {

        return email + " email OK" + "\nCount is " + count + "\n" + greet;
    }

    @GetMapping(path = "/pathVariable/{var_name}")
    public String helloWorldPathVariable(@PathVariable("var_name")
            @Pattern(regexp = "[a-zA-Z]+", message = "Path variable must contain only alphabetical characters")
            String name) {
        return String.format("The Value returned is %s", name);
    }

    @PostMapping("/reqBody")
    public ResponseEntity<ExampleResponse> handleExampleRequest(@Valid @RequestBody ExampleRequest exampleRequest) {

        return ResponseEntity.ok(ExampleResponse.builder()
                        .name(exampleRequest.getName())
                        .zonedDateTime(ZonedDateTime.now())
                .build());

    }

}