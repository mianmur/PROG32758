package ca.sheridancollege.murtazamianfinal.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long course_id;

    @Column(name = "name")
    private String name;

    @Column(name = "grade")
    private Long grade;


    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;

}
