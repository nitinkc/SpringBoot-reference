package com.springHelloWorld.controller;

import com.springHelloWorld.model.HelloWorldReturnBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/health")
public class AControllerWithResponseBody {

    @RequestMapping(
            path = "/check",
            method = RequestMethod.GET
    )    public @ResponseBody String checkHealth() {
        return "Health is Ok";
    }

    @RequestMapping(method= RequestMethod.GET,path="/requestMapping")
    public @ResponseBody HelloWorldReturnBean helloWorldViaRequestMapping() {
        return HelloWorldReturnBean.builder().message("Hello World").build();
    }
}