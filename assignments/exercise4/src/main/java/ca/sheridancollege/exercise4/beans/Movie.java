package ca.sheridancollege.exercise4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {
    private String name;
    private String director;

    @DateTimeFormat(pattern = "yyyy")
    private Date yrRelease;
}
