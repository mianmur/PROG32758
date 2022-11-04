package ca.sheridancollege.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CookieController {

    @GetMapping("/startCookie")
    public String startCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("name", "Falcon");
        response.addCookie(cookie);
        return "redirect:/cookie";
    }

    @GetMapping("/cookie")
    public String nextCookie(@CookieValue(required = false) String name){
        if (name==null) {
            System.out.println("Cookie did not come back!");
        } else {
            System.out.println("Cookie came back: " + name);
        }
        return "cookie";
    }
}
