package com.adidas.backend.adiclubservice.infrastructure.api;

import com.adidas.backend.adiclubservice.domain.NotRegisteredEmailsException;
import com.adidas.backend.adiclubservice.usecase.RetrieveRegisteredEmails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    private final RetrieveRegisteredEmails retrieveRegisteredEmails;

    public EmailController(RetrieveRegisteredEmails retrieveRegisteredEmails) {
        this.retrieveRegisteredEmails = retrieveRegisteredEmails;
    }

    @GetMapping
    public List<String> getEmailsFromMembers() throws NotRegisteredEmailsException {
        return retrieveRegisteredEmails.execute();
    }

}
