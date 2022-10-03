package com.example.demo.controllers;

import com.example.demo.beans.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//    @Autowired
//    private School school;

//    private School school;
//    @Autowired
//    public void setSchool(School school) {this.school = school;}

    private School school; // best practise
    @Autowired
    public HomeController(School school) { this.school = school; }

    @GetMapping("/")
    public String goHome() {
        System.out.println("--- From Controller");
        System.out.println(school);
        return "index";
    }

}
