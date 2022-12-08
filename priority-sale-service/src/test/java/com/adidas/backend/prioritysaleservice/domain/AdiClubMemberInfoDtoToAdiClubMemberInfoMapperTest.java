package com.adidas.backend.prioritysaleservice.domain;

import com.adidas.backend.prioritysaleservice.infrastructure.client.dto.AdiClubMemberInfoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class AdiClubMemberInfoDtoToAdiClubMemberInfoMapperTest {

    private AdiClubMemberInfoDtoToAdiClubMemberInfoMapper testSubject;

    @BeforeEach
    void setUp() {
        this.testSubject = new AdiClubMemberInfoDtoToAdiClubMemberInfoMapper();
    }

    @Test
    void should_map_a_AdiClubMemberInfoDto_to_AdiClubMemberInfo() {
        final Instant registrationDate = Instant.now();
        final AdiClubMemberInfoDto adiClubMemberInfo = AdiClubMemberInfoDto.builder()
                .email("email1@gmail.com").points(500).registrationDate(registrationDate)
                .build();
        final AdiClubMemberInfo expected = new AdiClubMemberInfo("email1@gmail.com", 500, registrationDate);

        final AdiClubMemberInfo result = testSubject.map(adiClubMemberInfo);

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expected);

    }

}