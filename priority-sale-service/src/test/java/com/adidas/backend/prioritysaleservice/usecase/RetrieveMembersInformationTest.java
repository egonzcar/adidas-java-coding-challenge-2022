package com.adidas.backend.prioritysaleservice.usecase;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMemberInfo;
import com.adidas.backend.prioritysaleservice.domain.AdiClubMemberInfoDtoToAdiClubMemberInfoMapper;
import com.adidas.backend.prioritysaleservice.infrastructure.client.AdiClubServiceFeignClient;
import com.adidas.backend.prioritysaleservice.infrastructure.client.dto.AdiClubMemberInfoDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RetrieveMembersInformationTest {

    @Mock
    private AdiClubServiceFeignClient adiClubServiceFeignClient;
    @Mock
    private AdiClubMemberInfoDtoToAdiClubMemberInfoMapper mapper;


    private RetrieveMembersInformation testSubject;

    @BeforeEach
    void setup() {
        testSubject = new RetrieveMembersInformation(adiClubServiceFeignClient, mapper);
    }

    @Test
    void should_retrieve_registered_emails() {
        final Instant registrationDate = Instant.now();
        final AdiClubMemberInfoDto adiClubMemberInfoDto = AdiClubMemberInfoDto.builder()
                .email("email1@gmail.com").points(500).registrationDate(registrationDate)
                .build();
        given(adiClubServiceFeignClient.getAdiClubMemberInfo("email1@gmail.com"))
                .willReturn(adiClubMemberInfoDto);
        given(mapper.map(adiClubMemberInfoDto))
                .willReturn(new AdiClubMemberInfo("email1@gmail.com", 500, registrationDate));

        final List<AdiClubMemberInfo> result = testSubject.execute(List.of("email1@gmail.com"));

        final List<AdiClubMemberInfo> expected = List.of(new AdiClubMemberInfo("email1@gmail.com", 500, registrationDate));
        Assertions.assertThat(result)
                .isNotEmpty()
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

}