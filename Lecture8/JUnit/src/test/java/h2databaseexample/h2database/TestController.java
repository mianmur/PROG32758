package h2databaseexample.h2database;

import h2databaseexample.h2database.database.DatabaseAccess;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class TestController {

    private DatabaseAccess da;
    private MockMvc mvc;

    @Autowired
    public void setDa(DatabaseAccess da){
        this.da = da;
    }

    @Autowired
    public void setMvc(MockMvc mvc){
        this.mvc = mvc;
    }

    @Test
    public void testRoot() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}
