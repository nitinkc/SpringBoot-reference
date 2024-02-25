package com.springHelloWorld.controller;

import com.springHelloWorld.model.HelloWorldReturnBean;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

/**
 * Created by nichaurasia on Saturday, December/07/2019 at 3:00 PM
 */
@RestController
@RequestMapping("/api/v0/hello-world")
public class BHelloWorldRestController {

    @GetMapping(path="/getMapping")
    public String helloWorldViaGetMapping() {
        return "Hello World - From getMapping";
    }

    @GetMapping(path="/getBean")
    public HelloWorldReturnBean helloWorldReturnBean() {
        return new HelloWorldReturnBean("Hello World - From HelloWorldReturnBean", ZonedDateTime.now());
    }
}