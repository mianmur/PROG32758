package ca.sheridancollege.securityexample.controllers;

import ca.sheridancollege.securityexample.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    DatabaseAccess da;

    @GetMapping("/")
    public String goHome(){
        return "index";
    }

    @GetMapping("/user")
    public String goToUserSecured(){
        return "secured/user/index";
    }

    @GetMapping("/manager")
    public String goToManagerSecured(){
        return "secured/manager/index";
    }

    @GetMapping("/secured")
    public String goToSecured(){
        return "secured/gateway";
    }

    @GetMapping("/login")
    public String login(){ return "login"; }

    @GetMapping("/permission-denied")
    public String goToDenied(){ return "error/permission-denied"; }

    @GetMapping("/manager/new-user")
    public String newUser(Model model){
        List<String> authorities = da.getAuthorities();

        model.addAttribute("authorities", authorities);

        return "/secured/manager/new-user";
    }

    @Autowired
    @Lazy
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @PostMapping("/manager/add-user")
    public String addUser(@RequestParam String userName,
                          @RequestParam String password,
                          @RequestParam String[] authorities, Model model) {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        for (String authority : authorities) {
            authorityList.add(new SimpleGrantedAuthority(authority));
        }
        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(userName, encodedPassword, authorityList);

        jdbcUserDetailsManager.createUser(user);

        model.addAttribute("message", "User successfully added");
        return "/secured/gateway";

    }
}
