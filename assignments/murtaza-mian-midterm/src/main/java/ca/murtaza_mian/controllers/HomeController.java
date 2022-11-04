package ca.murtaza_mian.controllers;

import ca.murtaza_mian.beans.SiteUser;
import ca.murtaza_mian.database.DatabaseAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private ArrayList<SiteUser> userList = new ArrayList<>();
    private DatabaseAccess database;
    public HomeController(DatabaseAccess database) {this.database = database;}

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new SiteUser());
        model.addAttribute("array", userList);
        return "index";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute SiteUser user, Model model){
        List<SiteUser> users = database.getUsers();
        model.addAttribute("userList", users);
        model.addAttribute("user", user);
        if (database.getUser(user) != null) {
            userList.clear();
            return "welcome";
        } else {
            userList.clear();
            return "invalid";
        }
    }

    @GetMapping("/register")
    public String registration(Model model){
        model.addAttribute("user", new SiteUser());
        return "register";
    }

    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute SiteUser user){
        int returnValue = database.addUser(user);
        userList.add(user);
        System.out.println("return value is: " + returnValue);
        return "redirect:/";
    }

    @GetMapping("/invalid")
    public String invalid(@ModelAttribute SiteUser user){
        return "invalid";
    }

}