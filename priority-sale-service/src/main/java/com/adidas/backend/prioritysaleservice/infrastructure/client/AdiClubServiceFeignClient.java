package com.adidas.backend.prioritysaleservice.infrastructure.client;

import com.adidas.backend.prioritysaleservice.infrastructure.client.dto.AdiClubMemberInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "adiclub-service", url = "${feign.adiclub-service.url}")
public interface AdiClubServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/adiclub")
    AdiClubMemberInfoDto getAdiClubMemberInfo(@RequestParam(value = "emailAddress") final String emailAddress);

    @RequestMapping(method = RequestMethod.GET, value = "/email")
    List<String> getAdiClubMemberEmails();

}