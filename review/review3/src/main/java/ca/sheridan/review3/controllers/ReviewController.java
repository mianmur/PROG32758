package ca.sheridan.review3.controllers;

import ca.sheridan.review3.beans.Book;
import ca.sheridan.review3.beans.Review;
import ca.sheridan.review3.database.BookRepository;
import ca.sheridan.review3.database.DatabaseAccess;
import ca.sheridan.review3.database.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReviewController {
    @Autowired
    DatabaseAccess da;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/book/{id}/addReviewPage")
    public String addingReview(@PathVariable Long id, Model model) {
        if (bookRepository.findById(id).isPresent()) {
            Book book = bookRepository.findById(id).get();

            model.addAttribute("book", book);
        }
        model.addAttribute("review", new Review());
        return "/user/add-review";
    }

    @PostMapping("/book/{id}/addReview")
    public String postReview(@PathVariable Long id,
                             @ModelAttribute Review review) {
        Book book = bookRepository.findById(id).get();
        List<Review> reviews = book.getReviews();
        review.setBook(book);
        reviews.add(review);
        reviewRepository.saveAll(reviews);

        return "redirect:/book/"+id;
    }

}
