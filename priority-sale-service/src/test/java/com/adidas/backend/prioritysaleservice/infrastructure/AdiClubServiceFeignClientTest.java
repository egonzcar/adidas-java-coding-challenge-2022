package com.adidas.backend.prioritysaleservice.infrastructure;

import com.adidas.backend.prioritysaleservice.infrastructure.client.AdiClubServiceFeignClient;
import com.adidas.backend.prioritysaleservice.infrastructure.client.dto.AdiClubMemberInfoDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AdiClubServiceFeignClientTest {

    @Mock
    private AdiClubServiceFeignClient testSubject;

    @Test
    void should_return_member_information() {
        final Instant registrationDate = Instant.ofEpochMilli(1669919859);
        given(testSubject.getAdiClubMemberInfo("ernesto@gmail.com"))
                .willReturn(AdiClubMemberInfoDto.builder()
                        .email("ernesto@gmail.com")
                        .points(5600)
                        .registrationDate(registrationDate)
                        .build());

        final AdiClubMemberInfoDto adiClubMemberInfo = testSubject.getAdiClubMemberInfo("ernesto@gmail.com");

        final AdiClubMemberInfoDto expected = AdiClubMemberInfoDto.builder()
                .email("ernesto@gmail.com")
                .points(5600)
                .registrationDate(registrationDate)
                .build();
        assertThat(adiClubMemberInfo)
                .isNotNull()
                .isEqualTo(expected);
    }

}