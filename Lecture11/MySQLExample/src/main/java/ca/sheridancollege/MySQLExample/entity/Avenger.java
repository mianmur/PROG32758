package ca.sheridancollege.MySQLExample.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Avenger {

    @Id
    private int id;
    private String name;
}
