package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class DemoController {


    @GetMapping({"", "/hello"})
    public String helloWorld(){
        return "index";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam(defaultValue = "off") String rememberMe) {
        System.out.println(firstName + " " + lastName + " " + rememberMe);
        return "success";
    }

    @GetMapping("/success")
    public String getDemo() { return "success"; }


    @GetMapping("/register_page")
    public String register() { return "register"; }
}
