package ru.senya.dossier.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.senya.dossier.controller.exceptionHandler.exception.EmailMessageProcessingException;
import ru.senya.dossier.entity.dto.EmailMessage;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmailMessageServiceTest {

    @InjectMocks
    private EmailMessageService emailMessageService;

    @Test
    @DisplayName("Ошибка при маппинге в сущность EmailMessage")
    void emailMessageProcessingExceptionTest() {

        assertThrows(EmailMessageProcessingException.class, () -> emailMessageService.convertToEmailMessage(TestServiceData.getUnmappableString()));

    }

    @Test
    @DisplayName("Маппинг в сущность EmailMessage происходит корректно")
    void emailMessageMappedCorrectlyTest() {

        assertInstanceOf(EmailMessage.class, emailMessageService.convertToEmailMessage(TestServiceData.getCorrectEmailMessageString()));
    }


}