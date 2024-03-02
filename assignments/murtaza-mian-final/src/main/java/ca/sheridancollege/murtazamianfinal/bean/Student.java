package ca.sheridancollege.murtazamianfinal.bean;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;

    @Column(name = "number")
    private Long number;
    @Column(name = "name")
    private String name;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy =
            "student")
    private List<Course> courses = new ArrayList<>();

    public Student(Long student_id, Long number, String name) {
        this.student_id=student_id;
        this.number=number;
        this.name=name;
    }
}
