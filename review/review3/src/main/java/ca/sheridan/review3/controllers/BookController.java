package ca.sheridan.review3.controllers;

import ca.sheridan.review3.beans.Book;
import ca.sheridan.review3.beans.Review;
import ca.sheridan.review3.repository.BookRepository;
import ca.sheridan.review3.security.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    DatabaseAccess da;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/book/{id}")
    public String reviews(@PathVariable Long id, Model model){

        if (bookRepository.findById(id).isPresent()) {

            Book book = bookRepository.findById(id).get();
            List<Review> reviews = book.getReviews();

            model.addAttribute("reviews", reviews);
            model.addAttribute("book", book);
        } else { return null; }

        return "view-book";
    }

    @GetMapping("/addPage")
    public String goToAdd(Model model) {
        model.addAttribute("book", new Book());
        return "/admin/add-book";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book){
        bookRepository.save(book);
        return "redirect:/";
    }
}
