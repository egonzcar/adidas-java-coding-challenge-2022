package com.adidas.backend.publicservice.controller;

import com.adidas.backend.publicservice.usecase.SendRafflePetition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RaffleRestControllerTest {

    @Mock
    private SendRafflePetition sendRafflePetition;

    private RaffleRestController testSubject;

    @BeforeEach
    void setUp() {
        this.testSubject = new RaffleRestController(sendRafflePetition);
    }

    @Test
    void should_start_raffle_process() {
        final Throwable throwable = catchThrowable(() -> testSubject.startRaffle());

        assertThat(throwable).isNull();
        verify(sendRafflePetition, times(1)).execute();
    }

}