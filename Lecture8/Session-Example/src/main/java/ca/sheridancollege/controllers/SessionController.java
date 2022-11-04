package ca.sheridancollege.controllers;

import ca.sheridancollege.beans.Avenger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SessionController {

    @GetMapping("/startSession")
    public String startSession(HttpSession session) {
        if (session.isNew()) {
            session.setAttribute("message", "Welcome!");
        } else {
            session.setAttribute("message", "Welcome back!");
        }
        return "session";
    }

    @GetMapping("/killSession")
    public String killSession(HttpSession session) {
        session.invalidate();
        return "session";
    }

    @GetMapping("/startAvengerSession")
    public String startAvengerSession(Model model, HttpSession session) {

        List<Avenger> avengers = new ArrayList<>();

        session.setAttribute("avengerList", avengers);
        model.addAttribute("avenger", new Avenger());
        return "avengers";
    }
}
