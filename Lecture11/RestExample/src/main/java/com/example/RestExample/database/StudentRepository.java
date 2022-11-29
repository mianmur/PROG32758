package com.example.RestExample.database;

import com.example.RestExample.beans.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Student save(Student student);
    void deleteById(Long id);
    List<Student> findAll();
}
