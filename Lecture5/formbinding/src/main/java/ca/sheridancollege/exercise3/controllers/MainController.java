package ca.sheridancollege.exercise3.controllers;

import ca.sheridancollege.exercise3.beans.School;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class MainController {

    private ArrayList<School> schoolList = new ArrayList<>();

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("schoolList", schoolList);
        return "index";
    }

    @GetMapping("/registerPage")
    public String registration(Model model) {
        model.addAttribute("school", new School());
        return "registerSchool";
    }

    @PostMapping("/addSchool")
    public String addSchool(Model model, @ModelAttribute School school)
    {
        schoolList.add(school);
        model.addAttribute("schoolList", schoolList);
        return "redirect:/";
    }
    @GetMapping("/removeSchools")
    public String removeSchools()
    {
        schoolList.clear();
        return "redirect:/";
    }
    @GetMapping("/schoolView")
    public String viewSchools(Model model){
        model.addAttribute("schoolList", schoolList);
        return "viewSchools";
    }


}
