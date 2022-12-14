package ca.sheridan.review3.controllers;

import ca.sheridan.review3.beans.Book;
import ca.sheridan.review3.beans.ErrorMessage;
import ca.sheridan.review3.beans.Review;
import ca.sheridan.review3.database.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class RestfulController {

    //Our database access class
    @Autowired
    private BookRepository bookRepository;


    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    //use pathvariable to get the object {id} in this case
    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        if (bookRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(bookRepository.findById(id).get());
        }
         else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage("No such record"));
        }
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<?> getBookReviews(@PathVariable Long id) {
        List<Review> reviews = bookRepository.findById(id).get().getReviews();
        if (reviews != null && reviews.size() > 0) {
            return ResponseEntity.ok(reviews);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage("No such records"));
        }
    }
}
