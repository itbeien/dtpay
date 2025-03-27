/*
package cn.itbeien.task;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class MockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogin(){
        try {
            mockMvc.perform(post("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"name\":\"itbeien\"}"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
*/
