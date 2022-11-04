package h2databaseexample.h2database.repository;

import h2databaseexample.h2database.beans.Avenger;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AvengerRepository extends CrudRepository<Avenger, Long> {

    Avenger save(Avenger avenger);
    void deleteById(Long id);

    List<Avenger> findAll();
}
