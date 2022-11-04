package h2databaseexample.h2database;

import h2databaseexample.h2database.beans.Avenger;
import h2databaseexample.h2database.database.DatabaseAccess;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestDatabase {

    private DatabaseAccess da;

    @Autowired
    public void setDa(DatabaseAccess da) {
        this.da = da;
    }

    @Test
    public void testDatabaseAdd() {
        Avenger avenger = new Avenger();
        avenger.setName("Starlord");
        avenger.setAge(30);

        int origSize = da.getAvengers().size();

        da.addAvenger(avenger);
        int newSize = da.getAvengers().size();

        assertEquals(newSize, origSize + 1);
    }
}
