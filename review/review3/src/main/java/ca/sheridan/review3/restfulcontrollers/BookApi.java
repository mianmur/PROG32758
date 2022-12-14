package ca.sheridan.review3.restfulcontrollers;

import ca.sheridan.review3.beans.Book;
import ca.sheridan.review3.exception.ErrorMessage;
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
public class BookApi {

    //Our database access class
    @Autowired
    private BookRepository bookRepository;

    //use pathvariable to get the object {id} in this case
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required =
            false) String title) {
        List<Book> books = new ArrayList<>();

        if (title == null)
            books.addAll(bookRepository.findAll());
        else
            books.addAll(bookRepository.findByTitle(title));

        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book _tutorial =
                bookRepository.save(new Book(book.getBook_id(),
                        book.getTitle(), book.getAuthor()));
        return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
    }
}
