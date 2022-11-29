package ca.sheridancollege.fragments.example.database;

import ca.sheridancollege.fragments.example.beans.Student;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class DatabaseAccess {

    private NamedParameterJdbcTemplate jdbc;

    public List<Student> getStudents() throws Exception {

        String query = "SELECT * FROM students";

        BeanPropertyRowMapper<Student> studentMapper =
                new BeanPropertyRowMapper<>(Student.class);

        List<Student> students = jdbc.query(query, studentMapper);

        return students;
    }


    public int addStudent(Student student) throws Exception {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "INSERT INTO students (username) VALUES (:username)";

        namedParameters.addValue("username", student.getUserName());

        return jdbc.update(query, namedParameters);
    }
}
