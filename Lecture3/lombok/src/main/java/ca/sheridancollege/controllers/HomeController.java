package ca.sheridancollege.controllers;

import ca.sheridancollege.beans.School;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    private School school; // best practise
    @Autowired
    public HomeController(School school) {
        this.school = school; }

    @GetMapping("/")
    public String goHome() {
//        System.out.println("--- From Controller");
        log.info("Inside home controller");
        System.out.println(school);
        return "index";
    }

}
