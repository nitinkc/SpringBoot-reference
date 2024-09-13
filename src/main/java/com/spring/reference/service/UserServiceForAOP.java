package com.spring.reference.service;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceForAOP {

    public String addUser(String username) {
        log.info("Adding user: " + username);
        return "Added user: " + username;
    }

    public String deleteUser(String username) {
        log.info("Deleting user: " + username);
        return "Deleted user: " + username;
    }
    public String updateUserExceptionally(String username) {
        log.info("Updating user: " + username);
        //To test Exception pointcut
        username.toUpperCase();//Throws NullPointerException if null is passed
        return "Updated user: " + username;
    }
}