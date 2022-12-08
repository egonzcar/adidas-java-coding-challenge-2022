package com.adidas.backend.publicservice.usecase;

import com.adidas.backend.publicservice.infrastructure.client.PrioritySaleServiceFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SendRafflePetitionTest {

    @Mock
    private PrioritySaleServiceFeignClient prioritySaleServiceFeignClient;

    private SendRafflePetition testSubject;

    @BeforeEach
    void setUp() {
        testSubject = new SendRafflePetition(prioritySaleServiceFeignClient);
    }

    @Test
    void should_execute_start_raffle_process_use_case() {
        testSubject.execute();

        verify(prioritySaleServiceFeignClient, times(1)).startRaffleProcess();
    }

}