package ru.senya.dossier.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.senya.dossier.controller.exceptionHandler.exception.LoanOfferProcessingException;
import ru.senya.dossier.entity.dto.LoanOfferDTO;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoanOfferServiceTest {

    @InjectMocks
    private LoanOfferService loanOfferService;

    @Test
    @DisplayName("Маппинг в класс LoanOfferDTO происходит корректно")
    void correctlyConvertToLoanOfferDTOTest() {
        assertInstanceOf(LoanOfferDTO.class, loanOfferService.convertToLoanOfferDTO(TestServiceData.getLoanOfferDTOCorrectString()));
    }

    @Test
    void convertToLoanOfferThrowsProcessingExceptionTest() {
        assertThrows(LoanOfferProcessingException.class, () -> loanOfferService.convertToLoanOfferDTO(TestServiceData.getUnmappableString()));
    }
}