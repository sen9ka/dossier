package ru.senya.dossier.controller.exceptionHandler.exception;

public class ApplicationNotFoundException extends RuntimeException{

    public ApplicationNotFoundException(String message) {
        super(message);
    }

}
