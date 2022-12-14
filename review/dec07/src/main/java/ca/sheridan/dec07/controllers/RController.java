package ca.sheridan.dec07.controllers;

import ca.sheridan.dec07.beans.Comment;
import ca.sheridan.dec07.beans.Tutorial;
import ca.sheridan.dec07.database.TutorialRepo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RController {
    private TutorialRepo cr;

    //We want to return all comments
    @GetMapping("/tutorials")
    public List<Tutorial> getTutorials(){

        List<Tutorial> listTutorials = cr.findAll();

        return listTutorials;

    }



}
