package ca.sheridan.review3.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "book")
//    @JoinColumn(name = "book_id")
    private List<Review> reviews = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    public Book(Long book_id, String title, String author) {
        this.book_id=book_id;
        this.title=title;
        this.author=author;
    }
    public void removeReviews() { this.reviews.clear();}
}
