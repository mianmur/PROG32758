package com.example.RestExample.database;

import com.example.RestExample.beans.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class DatabaseAccess {

    private NamedParameterJdbcTemplate jdbc;

    public DatabaseAccess(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
    public List<Student> getStudents(){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM students";
        //Will map a row coming in to an instance of Student
        BeanPropertyRowMapper<Student> studentMapper =
                new BeanPropertyRowMapper<>(Student.class);
        List<Student> students = jdbc.query(query, namedParameters, studentMapper);

        return students;
    }

    public Student getStudent(Long id){
        //create a new instance of MapSqlParameterSource for our use
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "SELECT * FROM students WHERE id = :id";
        namedParameters.addValue("id",id);

        //Will map a row coming in to an instance of Student
        BeanPropertyRowMapper<Student> studentMapper = new BeanPropertyRowMapper<Student>(Student.class);

        //query the database
        List<Student> students = jdbc.query(query, namedParameters, studentMapper);

        if(students.isEmpty()){
            return null; // error condition
        } else {
            return students.get(0); //will only have one
        }
    }

    public Long addStudent(Student student) throws Exception{
        //create a new instance of MapSqlParameterSource for our use
        MapSqlParameterSource namedParameters = 
                new MapSqlParameterSource();

        String query = "INSERT INTO students (name) VALUES (:name)";

        //add the parameters to our map
        namedParameters.addValue("name", student.getName());

        //we use a GeneratedKeyHolder to get the new row's id back
        KeyHolder generatedKey = new GeneratedKeyHolder();

        //using the 3-arg version of update
        int returnValue = jdbc.update(query, namedParameters, generatedKey);

        //get the key and send it back if successful
        Long studentId = (Long) generatedKey.getKey();

        return(returnValue>0) ? studentId: 0;
    }
}
