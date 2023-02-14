package ru.senya.dossier.controllers.exceptionHandler.exceptions;

public class PaymentScheduleProcessingException extends RuntimeException{

    public PaymentScheduleProcessingException(String msg) {
        super(msg);
    }

}
