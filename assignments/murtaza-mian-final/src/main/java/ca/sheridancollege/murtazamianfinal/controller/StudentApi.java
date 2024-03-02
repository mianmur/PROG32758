package ca.sheridancollege.murtazamianfinal.controller;

import ca.sheridancollege.murtazamianfinal.bean.Student;
import ca.sheridancollege.murtazamianfinal.database.StudentRepository;
import ca.sheridancollege.murtazamianfinal.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentApi {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required =
            false) String title) {
        List<Student> students = new ArrayList<>();

        if (title == null)
            students.addAll(studentRepository.findAll());
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " +
                        "Student with id = " + id));

        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
