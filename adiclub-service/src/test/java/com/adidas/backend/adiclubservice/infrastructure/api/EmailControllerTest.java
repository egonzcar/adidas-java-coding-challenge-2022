package com.adidas.backend.adiclubservice.infrastructure.api;

import com.adidas.backend.adiclubservice.domain.NotRegisteredEmailsException;
import com.adidas.backend.adiclubservice.usecase.RetrieveRegisteredEmails;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EmailControllerTest {

    @Mock
    private RetrieveRegisteredEmails retrieveRegisteredEmails;

    private EmailController testSubject;

    @BeforeEach
    public void setUp() {
        this.testSubject = new EmailController(retrieveRegisteredEmails);
    }

    @Test
    void should_retrieve_registered_emails() throws NotRegisteredEmailsException {
        given(retrieveRegisteredEmails.execute()).willReturn(List.of("user1@gmail.com",
                "user1@gmail.com",
                "user3@adiclub.com",
                "user4@adiclub.com"));

        final List<String> emailsFromMembers = testSubject.getEmailsFromMembers();

        Assertions.assertThat(emailsFromMembers)
                .isNotEmpty()
                .usingRecursiveComparison()
                .isEqualTo(List.of("user1@gmail.com",
                        "user1@gmail.com",
                        "user3@adiclub.com",
                        "user4@adiclub.com"));
    }

    @Test
    void should_throw_exception_not_registered_emails() throws NotRegisteredEmailsException {
        given(retrieveRegisteredEmails.execute()).willThrow(new NotRegisteredEmailsException());

        final Throwable throwable = catchThrowable(() -> testSubject.getEmailsFromMembers());

        Assertions.assertThat(throwable)
                .isNotNull()
                .isInstanceOf(NotRegisteredEmailsException.class)
                .hasMessage("There are no emails registered in the database.");
    }
}