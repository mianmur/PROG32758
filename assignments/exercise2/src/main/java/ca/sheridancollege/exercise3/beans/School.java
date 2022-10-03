package ca.sheridancollege.exercise3.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
    private String name;
    private String addr;
    private int numStudents;
}
