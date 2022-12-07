package com.adidas.backend.prioritysaleservice.infrastructure.api;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMemberInfo;
import com.adidas.backend.prioritysaleservice.usecase.RetrieveMembersInformation;
import com.adidas.backend.prioritysaleservice.usecase.RetrieveRegisteredEmails;
import com.adidas.backend.prioritysaleservice.usecase.SendEmail;
import com.adidas.backend.prioritysaleservice.usecase.SortMembersInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RaffleControllerTest {

    @Mock
    private RetrieveRegisteredEmails retrieveRegisteredEmails;
    @Mock
    private RetrieveMembersInformation retrieveMembersInformation;
    @Mock
    private SortMembersInformation sortMembersInformation;
    @Mock
    private SendEmail sendEmail;

    private RaffleController testSubject;

    @BeforeEach
    void setUp() {
        testSubject = new RaffleController(retrieveRegisteredEmails, retrieveMembersInformation, sortMembersInformation, sendEmail);
    }

    @Test
    void should_start_raffle_process() {
        final Instant registrationDate = Instant.now();
        final List<String> emailsList = List.of("user1@gmail.com", "user2@adiclub.com");
        final List<AdiClubMemberInfo> infoList = List.of(new AdiClubMemberInfo("user1@gmail.com", 500, registrationDate), new AdiClubMemberInfo("user2@adiclub.com", 1500, registrationDate));
        final List<AdiClubMemberInfo> sortedInfoList = List.of(new AdiClubMemberInfo("user2@adiclub.com", 1500, registrationDate), new AdiClubMemberInfo("user1@gmail.com", 500, registrationDate));

        given(retrieveRegisteredEmails.execute()).willReturn(emailsList);
        given(retrieveMembersInformation.execute(emailsList)).willReturn(infoList);
        given(sortMembersInformation.execute(infoList)).willReturn(sortedInfoList);

        final Throwable throwable = catchThrowable(() -> testSubject.startRaffle());

        assertThat(throwable).isNull();
        verify(retrieveRegisteredEmails, times(1)).execute();
        verify(retrieveMembersInformation, times(1)).execute(emailsList);
        verify(sortMembersInformation, times(1)).execute(infoList);
        verify(sendEmail, times(2)).execute(any(AdiClubMemberInfo.class));
    }

    @Test
    void should_fail_raffle_process_with_empty_email_list() {
        given(retrieveRegisteredEmails.execute()).willThrow(new RuntimeException("There are no emails registered in the database."));

        final Throwable throwable = catchThrowable(() -> testSubject.startRaffle());

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(RuntimeException.class)
                .hasMessage("There are no emails registered in the database.");

        verify(retrieveRegisteredEmails, times(1)).execute();
        verify(retrieveMembersInformation, never()).execute(any());
        verify(sortMembersInformation, never()).execute(any());
        verify(sendEmail, never()).execute(any());
    }

}