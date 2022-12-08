package com.adidas.backend.adiclubservice.infrastructure.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FakeDatabaseTest {

    private FakeDatabase testSubject;

    @BeforeEach
    public void setUp() {
        this.testSubject = new FakeDatabase();
    }

    @Test
    void should_create_list_of_registered_emails() {
        final List<String> registeredEmails = testSubject.retrieveRegisteredEmails();

        assertThat(registeredEmails)
                .isNotEmpty();
        System.out.println(registeredEmails);
    }

}