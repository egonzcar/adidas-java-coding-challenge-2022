package com.adidas.backend.adiclubservice.usecase;

import com.adidas.backend.adiclubservice.domain.EmailService;
import com.adidas.backend.adiclubservice.domain.NotRegisteredEmailsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RetrieveRegisteredEmailsTest {

    @Mock
    private EmailService emailService;

    private RetrieveRegisteredEmails testSubject;

    @BeforeEach
    public void setUp() {
        this.testSubject = new RetrieveRegisteredEmails(emailService);
    }

    @Test
    void should_retrieve_email_list() throws NotRegisteredEmailsException {
        given(emailService.retrieveRegisteredEmails()).willReturn(List.of("user1@gmail.com",
                "user1@gmail.com",
                "user3@adiclub.com",
                "user4@adiclub.com"));

        final List<String> result = testSubject.execute();

        assertThat(result)
                .isNotEmpty()
                .usingRecursiveComparison()
                .isEqualTo(List.of("user1@gmail.com",
                        "user1@gmail.com",
                        "user3@adiclub.com",
                        "user4@adiclub.com"));
    }

    @Test
    void should_fail_in_case_of_empty_list() {
        given(emailService.retrieveRegisteredEmails()).willReturn(List.of());

        final Throwable throwable = catchThrowable(() -> testSubject.execute());

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(NotRegisteredEmailsException.class)
                .hasMessage("There are no emails registered in the database.");
    }

}