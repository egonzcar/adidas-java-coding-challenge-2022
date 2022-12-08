package com.adidas.backend.publicservice.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "priority-sale-service", url = "${feign.priority-sale-service.url}")
public interface PrioritySaleServiceFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/raffle")
    void startRaffleProcess();

}
