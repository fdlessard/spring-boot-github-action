package io.fdlessard.codebites.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("integration")
@SpringBootTest
@ExtendWith(
        {
                SpringExtension.class
        }
)
public class SpringBootGithubActionApplicationIt {

    @Test
    void contextLoads() {
    }

}
