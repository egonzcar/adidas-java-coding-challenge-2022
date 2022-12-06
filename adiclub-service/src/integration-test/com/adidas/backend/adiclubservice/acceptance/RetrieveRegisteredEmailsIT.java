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
    private EmailController testSubject;

    @Test
    void retrieve_members_email() throws NotRegisteredEmailsException {
        final List<String> emailsFromMembers = testSubject.getEmailsFromMembers();

        assertThat(emailsFromMembers)
                .isNotEmpty();
        System.out.println(emailsFromMembers);
    }

}
