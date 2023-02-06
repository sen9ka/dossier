package ru.senya.dossier.controllers.exceptionHandler.exceptions;

public class ApplicationNotFoundException extends RuntimeException{

    public ApplicationNotFoundException(String message) {
        super(message);
    }

}
