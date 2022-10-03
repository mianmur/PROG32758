package com.example.demo.beans;

import org.springframework.stereotype.Component;

@Component
public class School {
    private String name;
    private int numStudents;

    public School() {
        this("Sheridan", 23000);
    }
    public School(String name, int numStudents) {
        this.name = name;
        this.numStudents = numStudents;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getNumStudents() {return numStudents;}
    public void setNumStudents(int numStudents) {this.numStudents = numStudents;}

    @Override
    public String toString(){
        return "School [name=" + name + ", numStudents=" + numStudents + "]";
    }
}
