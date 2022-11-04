package assignment2.main.repository;

import assignment2.main.beans.Regist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Regist, Long> {

    Regist save(Regist regist);
    void deleteById(Long id);

    List<Regist> findAll();

}
