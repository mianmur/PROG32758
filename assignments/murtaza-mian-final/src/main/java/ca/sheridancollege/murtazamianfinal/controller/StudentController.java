package ca.sheridancollege.murtazamianfinal.controller;

import ca.sheridancollege.murtazamianfinal.bean.Course;
import ca.sheridancollege.murtazamianfinal.bean.Student;
import ca.sheridancollege.murtazamianfinal.database.CourseRepository;
import ca.sheridancollege.murtazamianfinal.database.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;


    @GetMapping("/student/{id}/addCoursePage")
    public String addCourse(@PathVariable Long id, Model model) {
        if (studentRepository.findById(id).isPresent()) {
            Student student = studentRepository.findById(id).get();

            model.addAttribute("student", student);
        }
        model.addAttribute("course", new Course());
        return "add-course";
    }

    @PostMapping("/student/{id}/addCourse")
    public String postCourse(@PathVariable Long id,
                             @ModelAttribute Course course) {
        Student student = studentRepository.findById(id).get();
        List<Course> courses = student.getCourses();
        course.setStudent(student);
        courses.add(course);
        courseRepository.saveAll(courses);

        return "redirect:/view-student/?student_id=" + id;

    }


}
