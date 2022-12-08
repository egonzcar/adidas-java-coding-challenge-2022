package com.adidas.backend.prioritysaleservice.usecase;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMemberInfo;
import com.adidas.backend.prioritysaleservice.infrastructure.queue.EmailEvent;
import com.adidas.backend.prioritysaleservice.infrastructure.queue.MessageProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SendEmailTest {

    @Mock
    private MessageProducer messageProducer;

    private SendEmail testSubject;

    @BeforeEach
    void setUp() {
        this.testSubject = new SendEmail(messageProducer);
    }

    @Test
    void should_send_email_event() {
        final EmailEvent expectedEvent = new EmailEvent("email1@adiclub.com");

        final Throwable throwable = catchThrowable(() -> testSubject.execute(new AdiClubMemberInfo("email1@adiclub.com", 500, Instant.now())));

        assertThat(throwable).isNull();
        verify(messageProducer).sendMessage(eq(expectedEvent));
    }

}