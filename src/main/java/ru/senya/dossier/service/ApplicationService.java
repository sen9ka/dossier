package ru.senya.dossier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.senya.dossier.controller.exceptionHandler.exception.ApplicationNotFoundException;
import ru.senya.dossier.entity.model.Application;
import ru.senya.dossier.repository.ApplicationRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public Application findApplicationByApplicationId(Long applicationId) {
        Optional<Application> optionalApplication = applicationRepository.findById(applicationId);

        return optionalApplication.orElseThrow(() -> new ApplicationNotFoundException("Заявка с ID " + applicationId + " не найдена"));
    }

}
