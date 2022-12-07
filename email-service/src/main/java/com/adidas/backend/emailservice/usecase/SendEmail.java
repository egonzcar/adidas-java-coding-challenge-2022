package com.adidas.backend.emailservice.usecase;

import com.adidas.backend.emailservice.infrastructure.queue.EmailEvent;
import com.adidas.backend.emailservice.infrastructure.queue.MessageConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {

    private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    public void execute(final EmailEvent emailEvent) {
        //TODO some magic stuff to send email
        log.info(String.format("Send email to %s", emailEvent.getEmail()));
    }

}
