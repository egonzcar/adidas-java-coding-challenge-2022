package com.adidas.backend.prioritysaleservice.usecase;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMemberInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SortMembersInformationTest {

    private SortMembersInformation testSubject;

    @BeforeEach
    void setUp() {
        testSubject = new SortMembersInformation();
    }

    @Test
    void should_sort_member_information_list() {
        final AdiClubMemberInfo adiClubMember1 = new AdiClubMemberInfo("user1@gmail.com", 590, Instant.ofEpochMilli(1669919859).minus(4, ChronoUnit.DAYS));
        final AdiClubMemberInfo adiClubMember2 = new AdiClubMemberInfo("user2@gmail.com", 3000, Instant.ofEpochMilli(1669919859).minus(3, ChronoUnit.DAYS));
        final AdiClubMemberInfo adiClubMember3 = new AdiClubMemberInfo("user3@adiclub.com", 5100, Instant.ofEpochMilli(1669919859).minus(2, ChronoUnit.DAYS));
        final AdiClubMemberInfo adiClubMember4 = new AdiClubMemberInfo("user4@adiclub.com", 560, Instant.ofEpochMilli(1669919859).minus(1, ChronoUnit.DAYS));
        final List<AdiClubMemberInfo> adiClubMemberInfos = List.of(adiClubMember1, adiClubMember2, adiClubMember3, adiClubMember4);

        final List<AdiClubMemberInfo> result = testSubject.execute(adiClubMemberInfos);

        final List<AdiClubMemberInfo> expected = List.of(adiClubMember3, adiClubMember4, adiClubMember2, adiClubMember1);

        assertThat(result)
                .isNotEmpty()
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void should_sort_list_of_members_with_same_points() {
        final AdiClubMemberInfo adiClubMember1 = new AdiClubMemberInfo("user1@adiclub.com", 5100, Instant.ofEpochMilli(1669919859).minus(1, ChronoUnit.DAYS));
        final AdiClubMemberInfo olderAdiClubMember = new AdiClubMemberInfo("user4@adiclub.com", 5100, Instant.ofEpochMilli(1669919859).minus(20, ChronoUnit.DAYS));
        final AdiClubMemberInfo adiClubMember2 = new AdiClubMemberInfo("user2@adiclub.com", 6000, Instant.ofEpochMilli(1669919859).minus(10, ChronoUnit.DAYS));
        final AdiClubMemberInfo adiClubMember3 = new AdiClubMemberInfo("user3@adiclub.com", 600, Instant.ofEpochMilli(1669919859).minus(3, ChronoUnit.DAYS));
        final List<AdiClubMemberInfo> adiClubMemberInfos = List.of(adiClubMember1, olderAdiClubMember, adiClubMember2, adiClubMember3);

        final List<AdiClubMemberInfo> result = testSubject.execute(adiClubMemberInfos);

        final List<AdiClubMemberInfo> expected = List.of(adiClubMember2, olderAdiClubMember, adiClubMember1, adiClubMember3);

        assertThat(result)
                .isNotEmpty()
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

}