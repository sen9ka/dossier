package ru.senya.dossier.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import ru.senya.dossier.controller.exceptionHandler.exception.LoanOfferProcessingException;
import ru.senya.dossier.entity.dto.LoanOfferDTO;

@Service
public class LoanOfferService {

    public LoanOfferDTO convertToLoanOfferDTO(String data) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            return mapper.readValue(data, LoanOfferDTO.class);
        } catch (JsonProcessingException e) {
            throw new LoanOfferProcessingException(Constant.LOAN_OFFER_PROCESSING_EXCEPTION_TEXT);
        }
    }
}
