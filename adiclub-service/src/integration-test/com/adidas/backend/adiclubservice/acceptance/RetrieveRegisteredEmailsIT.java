package com.adidas.backend.adiclubservice.acceptance;

import com.adidas.backend.adiclubservice.domain.NotRegisteredEmailsException;
import com.adidas.backend.adiclubservice.infrastructure.api.EmailController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RetrieveRegisteredEmailsIT {

    @Autowired
    private EmailController emailController;

    @Test
    void retrieve_members_email() throws NotRegisteredEmailsException {
        final List<String> expected = List.of("user0@gmail.com", "user1@adiclub.com", "user2@gmail.com", "user3@adiclub.com", "user4@gmail.com", "user5@adiclub.com", "user6@gmail.com", "user7@adiclub.com", "user8@gmail.com", "user9@adiclub.com");

        final List<String> emailsFromMembers = emailController.getEmailsFromMembers();

        assertThat(emailsFromMembers)
                .isNotEmpty()
                .isEqualTo(expected);
    }

}
