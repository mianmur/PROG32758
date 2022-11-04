package h2databaseexample.h2database.controllers;

import h2databaseexample.h2database.beans.Avenger;
import h2databaseexample.h2database.repository.AvengerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private AvengerRepository avengerRepository;

    public MainController(AvengerRepository avengerRepository) {
        this.avengerRepository = avengerRepository;
    }

    @GetMapping("")
    public String index(Model model){
        List<Avenger> avengers = avengerRepository.findAll();
        model.addAttribute("avengerList", avengers);
        return "index";
    }

    @GetMapping("/addPage")
    public String goToAdd(Model model) {
        model.addAttribute("avenger", new Avenger());
        return "registerAvenger";
    }
    @PostMapping("/addAvenger")
    public String addAvenger(@ModelAttribute Avenger avenger)
    {
        avengerRepository.save(avenger);
        return "redirect:/";
    }

    @GetMapping("/deleteAvenger/{id}")
        public String deleteAvenger(@PathVariable Long id) {
        avengerRepository.deleteById(id);
        return "redirect:/";
    }

//    @GetMapping("/editAvenger/{id}")
//    public String editAvengerPage(@PathVariable Long id, Model model) {
//        Avenger avenger = database.getAvenger(id);
//
//        if (avenger==null) {
//            System.out.println("No result for id=" + id);
//            return "redirect:/";
//        }
//        model.addAttribute("avenger", avenger);
//        return "edit_avenger";
//    }
//
//    @PostMapping("/updateAvenger")
//    public String updateAvenger(@ModelAttribute Avenger avenger) {
//        int returnValue = database.editAvenger(avenger);
//        return "redirect:/";
//    }


}
//        @GetMapping("/registerPage")
//    public String registration(Model model) {
//        model.addAttribute("avenger", new Avenger());
//        return "registerAvenger";
//
//    }
