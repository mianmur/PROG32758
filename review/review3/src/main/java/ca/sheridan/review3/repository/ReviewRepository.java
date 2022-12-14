package ca.sheridan.review3.repository;

import ca.sheridan.review3.beans.Book;
import ca.sheridan.review3.beans.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> deleteAllByBook(Book book);
}
