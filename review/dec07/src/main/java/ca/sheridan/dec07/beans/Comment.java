package ca.sheridan.dec07.beans;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    private Long id;
    private String content;

    //don't need to add tut_id bc it's a reference in tutorial class with join column, so u don't need to specify
}
