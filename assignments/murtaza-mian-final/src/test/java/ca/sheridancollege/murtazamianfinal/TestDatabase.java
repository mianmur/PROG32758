package ca.sheridancollege.murtazamianfinal;
import ca.sheridancollege.murtazamianfinal.bean.Student;
import ca.sheridancollege.murtazamianfinal.database.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class TestDatabase {

    @Autowired
    @Resource
    @Mock
    private StudentRepository studentRepository;

    @Test
    public void testDatabaseAdd() {

        // we must use an ID higher than 2 (there are 2 in db already) and a
        // unique number not already used for this to work
        Student student = new Student(4L, 12434L, "john");

        int origSize = studentRepository.findAll().size();
        studentRepository.save(student);
        int newSize = studentRepository.findAll().size();

//        assertNotEquals(origSize, newSize);
        assertEquals(newSize, origSize + 1);
    }

    @Test
    public void testNonUniqueID() {
        Student student = new Student(1L, 1234L, "john");

        int origSize = studentRepository.findAll().size();
        studentRepository.save(student);
        int newSize = studentRepository.findAll().size();
        // they will both equal the exact same thing since no new user was
        // added. The id 1 is already taken by another student so this is
        // expected to fail
//        assertEquals(newSize, origSize);
//        assertNotEquals(newSize, origSize + 1);
        assertNotEquals(origSize+1, newSize, "No user with that ID can be " +
                "added");

    }

}
