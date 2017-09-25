package com.orieange.demo.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping("demo")
    @PreAuthorize("hasAuthority('666')")
    public String getDemo(){
        return "good";
    }
}
