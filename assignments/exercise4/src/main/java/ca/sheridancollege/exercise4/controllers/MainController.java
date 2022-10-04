package ca.sheridancollege.exercise4.controllers;

import ca.sheridancollege.exercise4.beans.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class MainController {

    private ArrayList<Movie> movieList = new ArrayList<>();

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("movieList", movieList);
        return "index";
    }

    @GetMapping("/addPage")
    public String registration(Model model) {
        model.addAttribute("movie", new Movie());
        return "addPage";
    }

    @PostMapping("/addMovie")
    public String addMovie(Model model, @ModelAttribute Movie movie)
    {
        movieList.add(movie);
        model.addAttribute("movieList", movieList);
        return "redirect:/";
    }

    @GetMapping("/resetDB")
    public String removeMovies()
    {
        movieList.clear();
        return "redirect:/";
    }

    @GetMapping("/movieDB")
    public String movieDB(Model model){
        model.addAttribute("movieList", movieList);
        return "movieDB";
    }

}
