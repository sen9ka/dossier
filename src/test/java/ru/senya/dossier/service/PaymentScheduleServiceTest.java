package ru.senya.dossier.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.senya.dossier.controller.exceptionHandler.exception.PaymentScheduleProcessingException;
import ru.senya.dossier.entity.dto.PaymentScheduleElement;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class PaymentScheduleServiceTest {

    @InjectMocks
    private PaymentScheduleService paymentScheduleService;

    @Test
    @DisplayName("Маппинг в класс PaymentScheduleElement происходит корректно")
    void correctPaymentScheduleElementMappingTest() {
        assertInstanceOf(PaymentScheduleElement.class, paymentScheduleService.getPaymentScheduleElementList(TestServiceData.getPaymentScheduleString()).get(0));
    }

    @Test
    @DisplayName("Некорректная строка выбрасывает PaymentScheduleProcessingException")
    void mappingToPaymentScheduleThrowsProcessingExceptionTest() {
        assertThrows(PaymentScheduleProcessingException.class, () -> paymentScheduleService.getPaymentScheduleElementList(TestServiceData.getUnmappableString()));
    }
}