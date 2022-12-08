package com.adidas.backend.prioritysaleservice.usecase;

import com.adidas.backend.prioritysaleservice.infrastructure.client.AdiClubServiceFeignClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RetrieveRegisteredEmailsTest {

    @Mock
    private AdiClubServiceFeignClient adiClubServiceFeignClient;

    private RetrieveRegisteredEmails testSubject;

    @BeforeEach
    void setup() {
        testSubject = new RetrieveRegisteredEmails(adiClubServiceFeignClient);
    }

    @Test
    void should_retrieve_registered_emails() {
        given(adiClubServiceFeignClient.getAdiClubMemberEmails()).willReturn(List.of("email1@gmail.com", "email2@adiclub.com"));

        final List<String> result = testSubject.execute();

        final List<String> expected = List.of("email1@gmail.com", "email2@adiclub.com");
        Assertions.assertThat(result)
                .isNotEmpty()
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

}