package h2databaseexample.h2database.database;

import h2databaseexample.h2database.beans.Avenger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {
    private NamedParameterJdbcTemplate jdbc;

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

//    public int addAvenger(Avenger avenger){
//        // create a new instance of MapSqlParameter for our use
//        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
//
//        String query = "INSERT INTO avengers (name, age) VALUES (:name, :age)";
//
//        //add the parameters to our map
//        namedParameters
//                .addValue("Name", avenger.getName())
//                .addValue("Age", avenger.getAge());
//
//        int returnValue = jdbc.update(query, namedParameters);
//        return returnValue;
//    }
}
