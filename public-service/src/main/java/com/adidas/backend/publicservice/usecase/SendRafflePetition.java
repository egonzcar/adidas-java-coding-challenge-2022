package com.adidas.backend.publicservice.usecase;

import com.adidas.backend.publicservice.infrastructure.client.PrioritySaleServiceFeignClient;
import org.springframework.stereotype.Component;

@Component
public class SendRafflePetition {

    private final PrioritySaleServiceFeignClient prioritySaleServiceFeignClient;

    public SendRafflePetition(PrioritySaleServiceFeignClient prioritySaleServiceFeignClient) {
        this.prioritySaleServiceFeignClient = prioritySaleServiceFeignClient;
    }

    public void execute() {
        prioritySaleServiceFeignClient.startRaffleProcess();
    }

}
