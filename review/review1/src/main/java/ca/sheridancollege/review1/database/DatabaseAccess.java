package ca.sheridancollege.review1.database;

import ca.sheridancollege.review1.beans.Users;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {

    private final NamedParameterJdbcTemplate jdbc;
    public DatabaseAccess(NamedParameterJdbcTemplate jdbc) {this.jdbc=jdbc;}

    public List<Users> getUsers(){
        MapSqlParameterSource namedParams = new MapSqlParameterSource();
        String query = "SELECT * FROM USERS";

        BeanPropertyRowMapper<Users> mapper =
                new BeanPropertyRowMapper<Users>(Users.class);

        return jdbc.query(query, namedParams, mapper);
    }

    public Users getUser(Integer id){
        MapSqlParameterSource namedParams = new MapSqlParameterSource();
        String query = "SELECT * FROM USERS where id=:id;";
        namedParams.addValue("id", id);

        BeanPropertyRowMapper<Users> mapper =
                new BeanPropertyRowMapper<>(Users.class);

        Users user = null;

        try {
            user = jdbc.queryForObject(query, namedParams, mapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        } return user;
    }

    public Integer addUser(Users user) throws Exception{
        //create a new instance of MapSqlParameterSource for our use
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource();

        String query = "INSERT INTO users (name, email, address) VALUES " +
                "(:name, :email, :address)";

        //add the parameters to our map
        namedParameters.addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("address", user.getAddress());

        //we use a GeneratedKeyHolder to get the new row's id back
        KeyHolder generatedKey = new GeneratedKeyHolder();

        //using the 3-arg version of update
        int returnValue = jdbc.update(query, namedParameters, generatedKey);

        //get the key and send it back if successful
        Long userId = (Long) generatedKey.getKey();

        return Math.toIntExact((returnValue > 0) ? userId : 0);
    }
}

