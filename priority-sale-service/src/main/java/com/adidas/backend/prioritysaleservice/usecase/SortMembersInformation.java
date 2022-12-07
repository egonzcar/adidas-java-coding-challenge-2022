package com.adidas.backend.prioritysaleservice.usecase;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMemberInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SortMembersInformation {

    public List<AdiClubMemberInfo> execute(final List<AdiClubMemberInfo> adiClubMemberInfos) {
        final List<AdiClubMemberInfo> adiClubMembers = adiClubMemberInfos.stream().filter(adiClubMember -> adiClubMember.getEmail().contains("@adiclub.com")).collect(Collectors.toList());
        final List<AdiClubMemberInfo> adiClubNormalUsers = new ArrayList<>(adiClubMemberInfos);
        adiClubNormalUsers.removeAll(adiClubMembers);

        sortListByRaffleCriteria(adiClubMembers);
        sortListByRaffleCriteria(adiClubNormalUsers);

        return Stream.concat(adiClubMembers.stream(), adiClubNormalUsers.stream())
                .collect(Collectors.toList());
    }

    private void sortListByRaffleCriteria(List<AdiClubMemberInfo> memberInfoList) {
        memberInfoList.sort(Comparator.comparing(AdiClubMemberInfo::getPoints).reversed().thenComparing(AdiClubMemberInfo::getRegistrationDate));
    }

}
