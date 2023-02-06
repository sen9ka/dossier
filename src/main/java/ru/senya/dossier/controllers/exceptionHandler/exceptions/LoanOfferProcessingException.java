package ru.senya.dossier.controllers.exceptionHandler.exceptions;

public class LoanOfferProcessingException extends RuntimeException{

    public LoanOfferProcessingException(String msg) {
        super(msg);
    }

}
