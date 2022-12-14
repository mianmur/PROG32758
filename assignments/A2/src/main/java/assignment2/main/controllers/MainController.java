package assignment2.main.controllers;

import assignment2.main.beans.Regist;
import assignment2.main.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private int test = 0;
    private UserRepository userRepository;

    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("regist", new Regist());
        List<Regist> regists = userRepository.findAll();
        model.addAttribute("registList", regists);
        model.addAttribute("test", test);
        return "index";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute Regist regist) {
        userRepository.save(regist);
        return "redirect:/";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("editPage");
        Regist regist = userRepository.findById(id).get();
        mav.addObject("regist", regist);
        return mav;
    }
}