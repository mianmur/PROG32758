package ca.sheridancollege.murtazamianfinal.controller;

import ca.sheridancollege.murtazamianfinal.bean.Course;
import ca.sheridancollege.murtazamianfinal.bean.Student;
import ca.sheridancollege.murtazamianfinal.database.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    public String goHome(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("studentList", students);
        model.addAttribute("student", new Student());
        return "index";
    }

    @GetMapping("/view-student/")
    public String view(@RequestParam Long student_id, Model model) {

        if (studentRepository.findById(student_id).isPresent()) {
            Student student = studentRepository.findById(student_id).get();
            List<Course> courses = student.getCourses();

            model.addAttribute("courses", courses);
            model.addAttribute("student", student);

        } else {
            return null;
        }
        return "view-students";
    }
}

// OLD METHOD FOR USING TABLE VIEW
//    @GetMapping("/student/{id}")
//    public String viewStudent(@PathVariable Long id, Model model) {
//        if (studentRepository.findById(id).isPresent()) {
//
//            Student student = studentRepository.findById(id).get();
//            List<Course> courses = student.getCourses();
//
//            model.addAttribute("courses", courses);
//            model.addAttribute("student", student);
//
//        } else {
//            return null;
//        }
//        return "view-students";
//    }