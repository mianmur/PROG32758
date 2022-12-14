package ca.sheridan.dec07.database;

import ca.sheridan.dec07.beans.Comment;
import ca.sheridan.dec07.beans.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialRepo extends CrudRepository<Tutorial, Long> {

    //finds all comments
    List<Tutorial> findAll();

    Tutorial findByTitle(String title);


}
