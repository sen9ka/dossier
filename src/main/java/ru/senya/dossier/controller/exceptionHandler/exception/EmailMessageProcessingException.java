package ru.senya.dossier.controller.exceptionHandler.exception;

public class EmailMessageProcessingException extends RuntimeException{
    public EmailMessageProcessingException(String msg) {
        super(msg);
    }
}
