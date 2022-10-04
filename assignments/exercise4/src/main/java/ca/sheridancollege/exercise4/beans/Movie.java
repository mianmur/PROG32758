package ca.sheridancollege.exercise4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {
    private String name;
    private String director;
    private int yrRelease;
}
