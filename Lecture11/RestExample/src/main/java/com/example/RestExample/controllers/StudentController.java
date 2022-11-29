package com.example.RestExample.controllers;

import com.example.RestExample.beans.ErrorMessage;
import com.example.RestExample.beans.Student;
import com.example.RestExample.database.DatabaseAccess;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//This is a special type of controller that is specialized for REST purposes
//It actually marshals our domain objects to and from json!
@RestController

//By using @RequestMapping at the class level we are defaulting
//Requests to /students here. It is a nice short hand way of using it
@RequestMapping("/students")
public class StudentController {

    //our database access class
    private DatabaseAccess da;

    //Autowire in the da through the constructor
    public StudentController(DatabaseAccess da){
        this.da=da;
    }
    /*method to return all students
            @return a list of students*/
    @GetMapping // will map get requests to /students
    public List<Student> getStudents(){
        return da.getStudents();
    }
    /*Handles requests for a specific student
    * @param id The of the student we want to return
    * @return A ResponseEntity. A spring supplied entity allowing
    * to quickly build and return http response messages*/

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id){
        // try and get the student from the database
        Student student = da.getStudent(id);

        if(student!=null){
            //student is there! Use responseEntity to return http OK
            //(200) and add the instance of student the body
            //The framework will convert this to json nicely!
            return ResponseEntity.ok(student);
        } else {
            //As per spec, return a Not found http response (404)
            //along with an error message in the body. Will also
            //be converted to json.
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage("No such record"));
        }
    }
    @PostMapping(consumes = "application/json") //type of data of what we expect
    public ResponseEntity<?> postStudent(@RequestBody Student student){
        try{ //we must use try as we put a uniqueness constraint

            //add the student and get back the id
            Long id = da.addStudent(student);

            //add it the instance of student
            student.setId(id);

            //build the uri for the newly inserted student to set it as the
            //location header for the http created (201) response
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(id).toUri();

            //return the properly created response
            return ResponseEntity.created(location).body(student);
        } catch (Exception e) {
            // couldn't add it! return http conflict (409) response
            //with an appropriate error message
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage("Name already exists."));
        }
    }
}
