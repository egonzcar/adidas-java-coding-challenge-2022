package com.adidas.backend.prioritysaleservice.usecase;

import com.adidas.backend.prioritysaleservice.infrastructure.client.AdiClubServiceFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetrieveRegisteredEmails {

    private final AdiClubServiceFeignClient adiClubServiceFeignClient;

    public RetrieveRegisteredEmails(AdiClubServiceFeignClient adiClubServiceFeignClient) {
        this.adiClubServiceFeignClient = adiClubServiceFeignClient;
    }

    public List<String> execute() {
        return adiClubServiceFeignClient.getAdiClubMemberEmails();
    }

}
