package com.spring.reference.controller;

import com.spring.reference.model.HelloWorldReturnBean;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/health")
public class A_ControllerWithResponseBody {

  // @Controller needs @ResponseBody with method name
  @RequestMapping(path = "/check", method = RequestMethod.GET)
  public @ResponseBody String checkHealth() {
        return "Health is Ok!!";
    }

    @RequestMapping(method= RequestMethod.GET,path="/requestMappingGetBean")
    public @ResponseBody HelloWorldReturnBean helloWorldViaRequestMapping() {
        String formattedCurrentTimeStamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS a z(O)"));
        return HelloWorldReturnBean.builder()
                .message("Hello World")
                .time(formattedCurrentTimeStamp)
                .build();
    }
}