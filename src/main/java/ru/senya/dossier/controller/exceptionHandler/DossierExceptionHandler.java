package ru.senya.dossier.controller.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.senya.dossier.controller.exceptionHandler.exception.PaymentScheduleProcessingException;
import ru.senya.dossier.controller.exceptionHandler.exception.ApplicationNotFoundException;
import ru.senya.dossier.controller.exceptionHandler.exception.EmailMessageProcessingException;
import ru.senya.dossier.controller.exceptionHandler.exception.LoanOfferProcessingException;

@ControllerAdvice
public class DossierExceptionHandler {
    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<Object> handleApplicationNotFoundException(ApplicationNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailMessageProcessingException.class)
    public ResponseEntity<Object> handleEmailMessageProcessingException(EmailMessageProcessingException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoanOfferProcessingException.class)
    public ResponseEntity<Object> handleLoanOfferProcessingException(LoanOfferProcessingException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentScheduleProcessingException.class)
    public ResponseEntity<Object> handlePaymentScheduleProcessingException(PaymentScheduleProcessingException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
