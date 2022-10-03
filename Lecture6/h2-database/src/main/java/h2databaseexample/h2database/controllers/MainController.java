package h2databaseexample.h2database.controllers;

import h2databaseexample.h2database.beans.Avenger;
import h2databaseexample.h2database.database.DatabaseAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private DatabaseAccess database;

    public MainController(DatabaseAccess database) {
        this.database = database;
    }
    private ArrayList<Avenger> avengerList = new ArrayList<>();

    @GetMapping("")
    public String index(Model model){
        List<Avenger> avengers = database.getAvengers();
        model.addAttribute("avengerList", avengers);
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
