package com.adidas.backend.prioritysaleservice.usecase;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMemberInfo;
import com.adidas.backend.prioritysaleservice.infrastructure.queue.EmailEvent;
import com.adidas.backend.prioritysaleservice.infrastructure.queue.MessageProducer;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {

    private final MessageProducer messageProducer;

    public SendEmail(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    public void execute(AdiClubMemberInfo memberInfo) {
        messageProducer.sendMessage(new EmailEvent(memberInfo.getEmail()));
    }

}
