package ca.sheridancollege.formbinding.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avenger {
    private String name;
    private int age;
    private String powerSource;

    private final String[] powerSources =
            {"Deity", "Magic", "Money", "Hulk"};

}
