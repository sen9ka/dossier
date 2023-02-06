package ru.senya.dossier.controllers.exceptionHandler.exceptions;

public class EmailMessageProcessingException extends RuntimeException{
    public EmailMessageProcessingException(String msg) {
        super(msg);
    }
}
