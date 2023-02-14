package ru.senya.dossier.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.senya.dossier.controller.exceptionHandler.exception.EmailMessageProcessingException;
import ru.senya.dossier.entity.dto.EmailMessage;

@Service
@RequiredArgsConstructor
public class EmailMessageService {

    public EmailMessage convertToEmailMessage(String data) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(data, EmailMessage.class);
        } catch (JsonProcessingException e) {
            throw new EmailMessageProcessingException(Constant.EMAIL_MESSAGE_PROCESSING_EXCEPTION_TEXT);
        }
    }

}
