package ca.sheridancollege.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class School {
    private String name;
    private String address;
    private int numStudents;

    public School(){
        this("Sheridan", "Oakville", 23000);
    }

}
