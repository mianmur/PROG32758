package ca.sheridancollege.review1.controllers;

import ca.sheridancollege.review1.beans.Users;
import ca.sheridancollege.review1.database.DatabaseAccess;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
@RequestMapping("")
public class RestController {

    private DatabaseAccess da;

    // return specific user
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id){
        Users user = da.getUser(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No record found");
        }
    }

    //return all users
    @GetMapping()
    public ResponseEntity<List<Users>> getUsers(){
        List<Users> user = da.getUsers();
        return ResponseEntity.ok(user);
    }

    //return adding a user
    @PostMapping(consumes = "applications/json")
    public ResponseEntity<?> addUser(@RequestBody Users user) {
        try {
            int id = da.addUser(user);
            user.setId(id);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(id).toUri();
            return ResponseEntity.created(location).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Name already exists.");
        }
    }
}
