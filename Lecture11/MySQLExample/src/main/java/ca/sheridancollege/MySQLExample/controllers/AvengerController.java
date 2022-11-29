package ca.sheridancollege.MySQLExample.controllers;

import ca.sheridancollege.MySQLExample.entity.Avenger;
import ca.sheridancollege.MySQLExample.repository.AvengerRepository;
import ca.sheridancollege.MySQLExample.services.AvengerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AvengerController {

    private AvengerServiceImpl ar;

    public AvengerController(AvengerServiceImpl avengerService) {this.ar=
            avengerService;}

    @GetMapping("/{id}")
    public ResponseEntity<Avenger> index(@PathVariable Integer id){
        Avenger avenger = ar.findById(id);
        return ResponseEntity.ok(avenger);
    }
}
