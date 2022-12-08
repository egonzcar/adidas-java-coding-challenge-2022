package com.adidas.backend.prioritysaleservice.domain;

import com.adidas.backend.prioritysaleservice.infrastructure.client.dto.AdiClubMemberInfoDto;
import org.springframework.stereotype.Component;

@Component
public class AdiClubMemberInfoDtoToAdiClubMemberInfoMapper {

    public AdiClubMemberInfo map(AdiClubMemberInfoDto adiClubMemberInfo) {
        return new AdiClubMemberInfo(adiClubMemberInfo.getEmail(),
                adiClubMemberInfo.getPoints(),
                adiClubMemberInfo.getRegistrationDate());
    }
}
