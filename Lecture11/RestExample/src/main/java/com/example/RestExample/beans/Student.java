package com.example.RestExample.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    private Long id;
    private String name;

    public Student(String name){
        this.name=name;
    }
}
