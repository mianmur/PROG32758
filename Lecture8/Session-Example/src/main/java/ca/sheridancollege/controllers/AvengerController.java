package ca.sheridancollege.controllers;

import ca.sheridancollege.beans.Avenger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AvengerController {

    @PostMapping("/addAvenger")
    public String addAvenger(@ModelAttribute Avenger avenger, Model model, HttpSession session) {

        @SuppressWarnings("unchecked")
        List<Avenger> avengers = (List<Avenger>)session.getAttribute("avengerList");

        avengers.add(avenger);
        model.addAttribute("avenger", new Avenger());

        return "avengers";
    }
}
