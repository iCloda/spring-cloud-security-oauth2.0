package com.orieange.auth.web;

import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.security.Security;

@RestController
public class UserController {

    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }

}
