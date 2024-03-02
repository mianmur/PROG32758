package ca.sheridancollege.murtazamianfinal.database;

import ca.sheridancollege.murtazamianfinal.bean.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
