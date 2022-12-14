package ca.sheridan.review3.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public List<String> getAuthorities() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        // using DISTINCT to not get duplicates
        String query = "SELECT DISTINCT authority FROM authorities";

        return jdbc.queryForList(query, namedParameters,
                String.class);
    }
}
