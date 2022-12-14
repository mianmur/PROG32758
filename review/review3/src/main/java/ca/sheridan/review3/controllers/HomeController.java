package ca.sheridan.review3.controllers;

import ca.sheridan.review3.beans.Book;
import ca.sheridan.review3.database.BookRepository;
import ca.sheridan.review3.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    DatabaseAccess da;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String goHome(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("bookList", books);
        return "index";
    }

    @GetMapping("/login")
    public String login(){ return "login"; }

    @GetMapping("/permission-denied")
    public String goToDenied(){ return "error/permission-denied"; }

    @GetMapping("/new-user")
    public String newUser(Model model){
        List<String> authorities = da.getAuthorities();
        model.addAttribute("authorities", authorities);
        return "register";
    }

    @Autowired
    @Lazy
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @PostMapping("/register")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String[] authorities, Model model) {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        for (String authority : authorities) {
            authorityList.add(new SimpleGrantedAuthority(authority));
        }
        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(username, encodedPassword, authorityList);

        jdbcUserDetailsManager.createUser(user);

        model.addAttribute("message", "User successfully added");
        return "login";

    }
}

