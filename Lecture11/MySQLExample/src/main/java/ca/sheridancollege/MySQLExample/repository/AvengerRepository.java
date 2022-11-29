package ca.sheridancollege.MySQLExample.repository;

import ca.sheridancollege.MySQLExample.entity.Avenger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvengerRepository extends CrudRepository<Avenger, Integer> {
        void deleteById(int id);
        List<Avenger> findAll();
        Avenger findById(int id);
}

