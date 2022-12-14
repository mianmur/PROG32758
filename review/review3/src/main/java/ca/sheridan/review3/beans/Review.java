package ca.sheridan.review3.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long review_id;

    @Column(name = "text")
    private String text;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name="book_id", referencedColumnName = "book_id")
    private Book book;
}
