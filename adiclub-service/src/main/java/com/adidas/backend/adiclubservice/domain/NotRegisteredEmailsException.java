package com.adidas.backend.adiclubservice.domain;

public class NotRegisteredEmailsException extends Exception {

    private static final String NOT_REGISTERED_EMAILS_EXCEPTION = "There are no emails registered in the database.";

    public NotRegisteredEmailsException() {
        super(NOT_REGISTERED_EMAILS_EXCEPTION);
    }

}