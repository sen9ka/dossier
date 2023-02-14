package ru.senya.dossier.controllers.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.senya.dossier.controllers.exceptionHandler.exceptions.ApplicationNotFoundException;
import ru.senya.dossier.controllers.exceptionHandler.exceptions.EmailMessageProcessingException;
import ru.senya.dossier.controllers.exceptionHandler.exceptions.LoanOfferProcessingException;
import ru.senya.dossier.controllers.exceptionHandler.exceptions.PaymentScheduleProcessingException;

@ControllerAdvice
public class DossierExceptionHandler {
    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<?> handleApplicationNotFoundException(ApplicationNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailMessageProcessingException.class)
    public ResponseEntity<?> handleEmailMessageProcessingException(EmailMessageProcessingException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoanOfferProcessingException.class)
    public ResponseEntity<?> handleLoanOfferProcessingException(LoanOfferProcessingException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentScheduleProcessingException.class)
    public ResponseEntity<?> handlePaymentScheduleProcessingException(PaymentScheduleProcessingException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
