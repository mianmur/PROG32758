package ca.sheridancollege.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HiddenFieldController {

    @GetMapping("/startHidden")
    public String startHidden(Model model){
        model.addAttribute("name", "Black Widow");
        return "hidden_1";
    }

    @GetMapping("/hidden2")
    public String nextHidden(@RequestParam(required = false) String name, Model model) {

        if (name != null) {
            model.addAttribute("name", name);
        }
        return "hidden_2";
    }
}
