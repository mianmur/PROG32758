package ca.sheridancollege.formbinding.controllers;

import ca.sheridancollege.formbinding.beans.Avenger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class MainController {

    private ArrayList<Avenger> avengerList = new ArrayList<>();

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("avengerList", avengerList);
        return "index";
    }

    @GetMapping("/registerPage")
    public String registration(Model model) {
        model.addAttribute("avenger", new Avenger());
        return "registerAvenger";
    }

    @PostMapping("/addAvenger")
    public String addAvenger(Model model, @ModelAttribute Avenger avenger)
    {
        avengerList.add(avenger);
        model.addAttribute("avengerList", avengerList);
        return "redirect:/";
    }


}
