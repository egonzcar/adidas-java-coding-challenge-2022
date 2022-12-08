package com.adidas.backend.prioritysaleservice.infrastructure.api;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMemberInfo;
import com.adidas.backend.prioritysaleservice.usecase.RetrieveMembersInformation;
import com.adidas.backend.prioritysaleservice.usecase.RetrieveRegisteredEmails;
import com.adidas.backend.prioritysaleservice.usecase.SendEmail;
import com.adidas.backend.prioritysaleservice.usecase.SortMembersInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/raffle")
public class RaffleController {

    private final static Logger log = LoggerFactory.getLogger(RaffleController.class);

    private final RetrieveRegisteredEmails retrieveRegisteredEmails;
    private final RetrieveMembersInformation retrieveMembersInformation;
    private final SortMembersInformation sortMembersInformation;
    private final SendEmail sendEmail;

    public RaffleController(final RetrieveRegisteredEmails retrieveRegisteredEmails,
                            final RetrieveMembersInformation retrieveMembersInformation,
                            final SortMembersInformation sortMembersInformation,
                            final SendEmail sendEmail) {
        this.retrieveRegisteredEmails = retrieveRegisteredEmails;
        this.retrieveMembersInformation = retrieveMembersInformation;
        this.sortMembersInformation = sortMembersInformation;
        this.sendEmail = sendEmail;
    }

    @PostMapping
    public void startRaffle() {
        final List<String> emailList = retrieveRegisteredEmails.execute();
        log.info("Email list: " + emailList);
        final List<AdiClubMemberInfo> adiClubMemberInfos = retrieveMembersInformation.execute(emailList);
        log.info("Info list: " + adiClubMemberInfos);
        final List<AdiClubMemberInfo> sortedMemberList = sortMembersInformation.execute(adiClubMemberInfos);
        log.info("Sorted list: " + sortedMemberList);

        sortedMemberList.forEach(sendEmail::execute);
    }

}
