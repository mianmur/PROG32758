package ca.sheridan.review3.database;

import ca.sheridan.review3.beans.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
