package ca.sheridan.dec07.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//https://coderanch.com/t/733652/java/package-javax-persistence-exist add the JAVAX PERSISTENCE DEPENDENCY
import javax.persistence.*;
import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tutorials")
public class Tutorial {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "tutorial_id")
    private List<Comment> comments = new ArrayList<>();

//    public List<Comment> getCommentList()
//    {
//        Iterator iterator = comments.iterator();
//        ArrayList<Comment> commentList = new ArrayList<Comment>();
//        while (iterator.hasNext())
//        {
//            commentList.add((Comment) iterator.next());
//        }
//        return commentList;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Boolean published;

}
