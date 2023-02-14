package ru.senya.dossier.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.senya.dossier.entity.dto.EmailMessage;
import ru.senya.dossier.service.EmailMessageService;
import ru.senya.dossier.service.EmailResponseService;

@Component
@RequiredArgsConstructor
@Tag(name = "Микросервис Dossier - чтение сообщений из кафки и отправка документов")
public class KafkaListeners {

    Logger logger = LoggerFactory.getLogger(KafkaListeners.class);
    private final EmailResponseService emailResponseService;
    private final EmailMessageService emailMessageService;

    @KafkaListener(topics = "finish-registration", groupId = "finishRegistration")
    @Operation(summary = "Отправка сообщения с выбранным условием")
    void finishRegistrationListener(String data) {
        logger.info(data);
        EmailMessage emailMessage = emailMessageService.convertToEmailMessage(data);
        emailResponseService.sendFinishRegistrationEmail(emailMessage.getApplicationId());
        logger.info("FinishRegistrationListener отправил сообщение");
    }

    @KafkaListener(topics = "create-documents", groupId = "createDocuments")
    @Operation(summary = "Отправка сообщения с расчитанным кредитом")
    void createDocumentsListener(String data) {
        logger.info(data);
        EmailMessage emailMessage = emailMessageService.convertToEmailMessage(data);
        emailResponseService.sendCreateDocumentsEmail(emailMessage.getApplicationId());
        logger.info("CreateDocumentsListener отправил сообщение");
    }

    @KafkaListener(topics = "send-documents", groupId = "sendDocuments")
    @Operation(summary = "Отправка сообщения с сформированными документами")
    void sendDocumentsListener(String data) {
        logger.info(data);
        EmailMessage emailMessage = emailMessageService.convertToEmailMessage(data);
        emailResponseService.sendSendDocumentsEmail(emailMessage.getApplicationId());
    }

    @KafkaListener(topics = "send-ses", groupId = "sendSes")
    @Operation(summary = "Отправка сообщения с кодом")
    void sendSesListener(String data) {
        logger.info(data);
        EmailMessage emailMessage = emailMessageService.convertToEmailMessage(data);
        emailResponseService.sendSesCodeEmail(emailMessage.getApplicationId());
    }

    @KafkaListener(topics = "application-denied", groupId = "sendSes")
    @Operation(summary = "Отправка сообщения об отказе")
    void applicationDeniedListener(String data) {
        logger.info(data);
        EmailMessage emailMessage = emailMessageService.convertToEmailMessage(data);
        emailResponseService.sendApplicationDeniedEmail(emailMessage.getApplicationId());
    }

}
