package ru.senya.dossier.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.senya.dossier.entity.dto.EmailMessage;
import ru.senya.dossier.services.EmailMessageService;
import ru.senya.dossier.services.EmailResponseService;

@Component
@RequiredArgsConstructor
public class KafkaListeners {

    Logger logger = LoggerFactory.getLogger(KafkaListeners.class);
    private final EmailResponseService emailResponseService;
    private final EmailMessageService emailMessageService;

    @KafkaListener(topics = "finish-registration", groupId = "finishRegistration")
    void FinishRegistrationListener(String data) {
        logger.info("Listener received" + data);
        EmailMessage emailMessage = emailMessageService.convertToEmailMessage(data);
        emailResponseService.sendFinishRegistrationEmail(emailMessage.getApplicationId());
        logger.info("FinishRegistrationListener отправил сообщение");
    }

    @KafkaListener(topics = "create-documents", groupId = "createDocuments")
    void CreateDocumentsListener(String data) {
        logger.info("Listener received" + data);
        EmailMessage emailMessage = emailMessageService.convertToEmailMessage(data);
        emailResponseService.sendCreateDocumentsEmail(emailMessage.getApplicationId());
        logger.info("CreateDocumentsListener отправил сообщение");
    }

    @KafkaListener(topics = "send-documents", groupId = "sendDocuments")
    void sendDocumentsListener(String data) {
        logger.info("Listener received" + data);
        EmailMessage emailMessage = emailMessageService.convertToEmailMessage(data);
        emailResponseService.sendSendDocumentsEmail(emailMessage.getApplicationId());
    }

    @KafkaListener(topics = "send-ses", groupId = "sendSes")
    void sendSesListener(String data) {
        logger.trace("Listener received" + data);
        EmailMessage emailMessage = emailMessageService.convertToEmailMessage(data);
        emailResponseService.sendSesCodeEmail(emailMessage.getApplicationId());
    }

    @KafkaListener(topics = "application-denied", groupId = "sendSes")
    void applicationDeniedListener(String data) {
        logger.trace("Listener received" + data);
        EmailMessage emailMessage = emailMessageService.convertToEmailMessage(data);
        emailResponseService.sendApplicationDeniedEmail(emailMessage.getApplicationId());
    }

}
