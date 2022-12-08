package com.adidas.backend.adiclubservice.usecase;

import com.adidas.backend.adiclubservice.domain.EmailService;
import com.adidas.backend.adiclubservice.domain.NotRegisteredEmailsException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetrieveRegisteredEmails {

    private final EmailService emailService;

    public RetrieveRegisteredEmails(EmailService emailService) {
        this.emailService = emailService;
    }

    public List<String> execute() throws NotRegisteredEmailsException {
        final List<String> registeredEmails = emailService.retrieveRegisteredEmails();
        if (registeredEmails.isEmpty()) throw new NotRegisteredEmailsException();
        return registeredEmails;
    }

}
