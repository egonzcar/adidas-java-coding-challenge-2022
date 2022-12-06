package com.adidas.backend.adiclubservice.infrastructure.persistence;

import com.adidas.backend.adiclubservice.domain.EmailService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeDatabase implements EmailService {

    private static final Integer REGISTERED = 10;

    @Override
    public List<String> retrieveRegisteredEmails() {
        final List<String> emailsRegistered = new ArrayList<>();
        for (int count = 0; count < REGISTERED; count++) {
            emailsRegistered.add(
                    "user" + count +
                            ((count % 2 == 0) ? "@gmail.com" : "@adiclub.com")
            );
        }
        return emailsRegistered;
    }

}
