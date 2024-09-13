package com.spring.reference.controller;

import com.spring.reference.dto.RefTableDTO;
import com.spring.reference.service.RefTableService;
import com.spring.reference.service.UserServiceForAOP;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class L_AopController {
    UserServiceForAOP userServiceForAOP;

    @GetMapping("/test/genericAop")
    public String getGenericApo(){
        return userServiceForAOP.addUser("testing AOP");
    }

    @GetMapping("/test/specificAop")
    public String getSpecificAop(){
        return userServiceForAOP.deleteUser("testing AOP");
    }

    @GetMapping("/test/exceptionAop")
    public String getRefDataById(){
        return userServiceForAOP.updateUserExceptionally(null);
    }
}