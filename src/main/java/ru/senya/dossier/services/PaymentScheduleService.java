package ru.senya.dossier.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import ru.senya.dossier.controllers.exceptionHandler.exceptions.PaymentScheduleProcessingException;
import ru.senya.dossier.entity.dto.PaymentScheduleElement;
import java.util.List;

@Service
public class PaymentScheduleService {

    public List<PaymentScheduleElement> getPaymentScheduleElementList(String data) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            return mapper.readValue(data, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new PaymentScheduleProcessingException("Ошибка при маппинге PaymentSchedule");
        }

    }

}
