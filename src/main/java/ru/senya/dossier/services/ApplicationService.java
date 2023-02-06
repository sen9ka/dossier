package ru.senya.dossier.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.senya.dossier.controllers.exceptionHandler.exceptions.ApplicationNotFoundException;
import ru.senya.dossier.entity.models.Application;
import ru.senya.dossier.repositories.ApplicationRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public Application findApplicationByApplicationId(Long applicationId) {
        Optional<Application> optionalApplication = applicationRepository.findByApplicationId(applicationId);

        return optionalApplication.orElseThrow(() -> new ApplicationNotFoundException("Заявка с ID " + applicationId + " не найдена"));
    }

}
