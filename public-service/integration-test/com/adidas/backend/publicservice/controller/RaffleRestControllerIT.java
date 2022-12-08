package com.adidas.backend.publicservice.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class RaffleRestControllerIT {

    @Autowired
    private RaffleRestController raffleRestController;

    @Test
    void should_start_a_raffle_process() {
        final Throwable throwable = catchThrowable(() -> raffleRestController.startRaffle());

        assertThat(throwable).isNull();
    }

}