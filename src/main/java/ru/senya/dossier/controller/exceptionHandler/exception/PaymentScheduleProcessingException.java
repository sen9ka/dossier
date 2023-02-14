package ru.senya.dossier.controller.exceptionHandler.exception;

public class PaymentScheduleProcessingException extends RuntimeException{

    public PaymentScheduleProcessingException(String msg) {
        super(msg);
    }

}
