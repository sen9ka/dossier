package ru.senya.dossier.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.senya.dossier.controller.exceptionHandler.exception.ApplicationNotFoundException;
import ru.senya.dossier.repository.ApplicationRepository;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    public static final long APPLICATION_ID = 1L;
    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ApplicationService applicationService;

    @Test
    @DisplayName("Не находит нужную заявку и выдает ошибку")
    void throwApplicationNotFoundExceptionTest() {

        when(applicationRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ApplicationNotFoundException.class, () -> applicationService.findApplicationByApplicationId(APPLICATION_ID));

    }
}