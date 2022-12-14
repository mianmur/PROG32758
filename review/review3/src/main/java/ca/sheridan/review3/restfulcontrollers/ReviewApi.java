package ca.sheridan.review3.restfulcontrollers;

import ca.sheridan.review3.beans.Book;
import ca.sheridan.review3.beans.Review;
import ca.sheridan.review3.repository.BookRepository;
import ca.sheridan.review3.repository.ReviewRepository;
import ca.sheridan.review3.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewApi{
    //Our database access class
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/book/{id}/reviews")
    public ResponseEntity<List<Review>> getAllReviewsByBookId(@PathVariable(value = "id") Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " +
                        "Tutorial with id = " + id));

        List<Review> reviews = new ArrayList<>(book.getReviews());

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/book/{id}/reviews")
    public ResponseEntity<Review> createReview(@PathVariable(value = "id") Long id,
                                                @RequestBody Review reviewRequest) {
        Book book = bookRepository.findById(id).get();
        List<Review> reviews = book.getReviews();
        reviewRequest.setBook(book);
        reviews.add(reviewRequest);
        reviewRepository.saveAll(reviews);
        return new ResponseEntity<>(reviewRequest, HttpStatus.CREATED);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable(value =
            "id") Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id));

        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable("id") long id,
                                                @RequestBody Review commentReview) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));

        review.setText(commentReview.getText());

        return new ResponseEntity<>(reviewRepository.save(review),
                HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable("id") long id) {
        reviewRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/book/{id}/reviews")
    public ResponseEntity<List<Review>> deleteAllReviewsOfBook(@PathVariable(value = "id") Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " +
                        "Tutorial with id = " + id));

        book.removeReviews();
        bookRepository.save(book);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
