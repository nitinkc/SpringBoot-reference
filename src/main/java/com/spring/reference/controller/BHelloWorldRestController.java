package com.spring.reference.controller;

import com.spring.reference.model.HelloWorldReturnBean;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nichaurasia on Saturday, December/07/2019 at 3:00 PM
 */
@RestController
@RequestMapping("/api/v0/hello-world")
public class BHelloWorldRestController {

    @GetMapping(path="/getMapping")
    public String helloWorldViaGetMapping() {
        return "Hello World - From getMapping!!!";
    }

    @GetMapping(path="/getBean")
    public HelloWorldReturnBean helloWorldReturnBean() {
        return new HelloWorldReturnBean("Hello World - From HelloWorldReturnBean",
                ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS a z(O)")));
    }
}