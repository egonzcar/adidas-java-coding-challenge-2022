package com.adidas.backend.prioritysaleservice.usecase;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMemberInfo;
import com.adidas.backend.prioritysaleservice.domain.AdiClubMemberInfoDtoToAdiClubMemberInfoMapper;
import com.adidas.backend.prioritysaleservice.infrastructure.client.AdiClubServiceFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RetrieveMembersInformation {

    private final AdiClubServiceFeignClient adiClubServiceFeignClient;
    private final AdiClubMemberInfoDtoToAdiClubMemberInfoMapper mapper;

    public RetrieveMembersInformation(AdiClubServiceFeignClient adiClubServiceFeignClient, AdiClubMemberInfoDtoToAdiClubMemberInfoMapper mapper) {
        this.adiClubServiceFeignClient = adiClubServiceFeignClient;
        this.mapper = mapper;
    }

    public List<AdiClubMemberInfo> execute(final List<String> memberMails) {
        return memberMails.stream()
                .map(adiClubServiceFeignClient::getAdiClubMemberInfo)
                .map(mapper::map)
                .collect(Collectors.toList());
    }

}
