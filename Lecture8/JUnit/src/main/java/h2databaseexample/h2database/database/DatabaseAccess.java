package h2databaseexample.h2database.database;

import h2databaseexample.h2database.beans.Avenger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {
    private final NamedParameterJdbcTemplate jdbc;
    public DatabaseAccess(NamedParameterJdbcTemplate jdbc) {
        this.jdbc=jdbc;
    }

    public List<Avenger> getAvengers() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM avengers";

        BeanPropertyRowMapper<Avenger> avengerMapper =
                new BeanPropertyRowMapper<Avenger>(Avenger.class);

        List<Avenger> avengers = jdbc.query(query, namedParameters, avengerMapper);

        return avengers;
    }

    public int addAvenger(Avenger avenger){
        // create a new instance of MapSqlParameter for our use
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO avengers (name, age) VALUES (:name, :age)";

        //add the parameters to our map
        namedParameters.addValue("name", avenger.getName()).addValue("age",
                avenger.getAge());

        int returnValue = jdbc.update(query, namedParameters);
        return returnValue;
    }

    public int deleteAvenger(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "DELETE FROM avengers WHERE id = :id";

        // add the parameter to our map
        namedParameters.addValue("id", id);

        int returnValue = jdbc.update(query, namedParameters);
        return returnValue;
    }

    public int editAvenger(Avenger avenger) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "UPDATE avengers SET name=:name, age=:age WHERE id = :id";

        namedParameters.addValue("name", avenger.getName()).addValue("age",
                avenger.getAge()).addValue("id", avenger.getId());

        int returnValue = jdbc.update(query, namedParameters);
        return returnValue;
    }

    public Avenger getAvenger(long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM avengers WHERE id = :id";
        parameters.addValue("id", id);

        BeanPropertyRowMapper<Avenger> mapper =
                new BeanPropertyRowMapper<>(Avenger.class);

        Avenger avenger = null;

        try {
            avenger = jdbc.queryForObject(query, parameters, mapper);
        } catch (EmptyResultDataAccessException ex) {
            System.out.println(ex.getMessage());
        } return avenger;
    }


}
