package com.adidas.backend.publicservice.controller;

import com.adidas.backend.publicservice.usecase.SendRafflePetition;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/raffle")
public class RaffleRestController {

    private final SendRafflePetition sendRafflePetition;

    public RaffleRestController(SendRafflePetition sendRafflePetition) {
        this.sendRafflePetition = sendRafflePetition;
    }

    @PostMapping
    public void startRaffle() {
        sendRafflePetition.execute();
    }

}
