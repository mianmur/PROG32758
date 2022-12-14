package ca.sheridan.dec07.controllers;

import ca.sheridan.dec07.beans.Tutorial;
import ca.sheridan.dec07.database.TutorialRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private TutorialRepo tr;

    //We want to return all comments
    @GetMapping("/")
    public String goHome(Model model){

        List<Tutorial> tutorials = tr.findAll();

        model.addAttribute("listTutorials", tutorials);

        return "index";
    }
}
