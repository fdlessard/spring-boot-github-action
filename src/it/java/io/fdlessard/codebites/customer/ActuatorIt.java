package io.fdlessard.codebites.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@ActiveProfiles("integration")
@ExtendWith(
        {
                SpringExtension.class
        }
)
public class ActuatorIt {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void actuator() throws Exception {

        mockMvc.perform(get("/actuator"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/vnd.spring-boot.actuator.v3+json"));
    }

    @Test
    public void health() throws Exception {

        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/vnd.spring-boot.actuator.v3+json"));

    }

    @Test
    public void info() throws Exception {

        mockMvc.perform(get("/actuator/info"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/vnd.spring-boot.actuator.v3+json"));

    }

}
