package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
            @RequestParam(defaultValue = "off") String rememberMe, Model model) {

        model.addAttribute("firstName", firstName);

        model.addAttribute("lastName", lastName);
        model.addAttribute("rememberMe", rememberMe);
        return "success";
    }

    @GetMapping("/success")
    public String getDemo() { return "success"; }

    @GetMapping("/register_page")
    public String register() { return "register"; }

    @GetMapping("/listings")
    public String getListings(Model model) {

//        hardcode array list for now
        List<String> members = new ArrayList<>();

        members.add("Irong Man"); members.add("Garmorah");
        members.add("Fat alcoholic thor"); members.add("Nebula");

//        adding arraylist members too te model
        model.addAttribute("members", members);
//      thymeleaf does the rest
        return "listings";
    }
}
