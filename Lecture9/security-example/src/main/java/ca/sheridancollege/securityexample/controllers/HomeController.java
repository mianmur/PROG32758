package ca.sheridancollege.securityexample.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String goHome(){
        return "index";
    }

    @GetMapping("/user")
    public String goToUserSecured(){
        return "secured/user/index";
    }

    @GetMapping("/manager")
    public String goToManagerSecured(){
        return "secured/manager/index";
    }
    @GetMapping("/secured")
    public String goToSecured(){
        return "secured/gateway";
    }
}
