package ru.senya.dossier.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import ru.senya.dossier.client.EmailClient;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailResponseServiceTest {

    public static final long APPLICATION_ID = 87L;
    @Mock
    private EmailClient emailClient;

    @Mock
    private ApplicationService applicationService;

    @Mock
    private LoanOfferService loanOfferService;

    @Mock
    private PaymentScheduleService paymentScheduleService;

    @InjectMocks
    private EmailResponseService emailResponseService;

    @Test
    @DisplayName("Корректное составление письма с завершением регистрации")
    void correctSendFinishRegistrationEmailTest() {
        when(applicationService.findApplicationByApplicationId(any())).thenReturn(TestServiceData.getCorrectTestApplication());
        when(loanOfferService.convertToLoanOfferDTO(any())).thenReturn(TestServiceData.getCorrectTestLoanOfferDTO());

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String expected = TestServiceData.getCorrectFinishRegistrationString();

        simpleMailMessage.setText(expected);
        when(emailClient.sendEmail(any(), any(), any())).thenReturn(simpleMailMessage);
        assertEquals(expected, emailResponseService.sendFinishRegistrationEmail(APPLICATION_ID).getText());
    }

    @Test
    @DisplayName("Корректное составление письма с созданием документов")
    void correctSendCreateDocumentsEmailTest() {
        when(applicationService.findApplicationByApplicationId(APPLICATION_ID)).thenReturn(TestServiceData.getCorrectTestApplication());
        when(paymentScheduleService.getPaymentScheduleElementList(any())).thenReturn(TestServiceData.getPaymentScheduleElementList());

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String expected = TestServiceData.getCorrectCreateDocumentsString();

        simpleMailMessage.setText(expected);

        when(emailClient.sendEmail(any(), any(), any())).thenReturn(simpleMailMessage);
        assertEquals(expected, emailResponseService.sendCreateDocumentsEmail(APPLICATION_ID).getText());
    }

    @Test
    @DisplayName("Корректное составление письма с отправкой документов")
    void correctSendSendDocumentsEmailTest() {
        when(applicationService.findApplicationByApplicationId(APPLICATION_ID)).thenReturn(TestServiceData.getCorrectTestApplication());
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String expected = TestServiceData.getCorrectSendDocumentsString();
        simpleMailMessage.setText(expected);

        when(emailClient.sendEmail(any(), any(), any())).thenReturn(simpleMailMessage);
        assertEquals(expected, emailResponseService.sendSendDocumentsEmail(APPLICATION_ID).getText());


    }

    @Test
    @DisplayName("Корректное составление письма с отправкой кода")
    void correctSendSesCodeEmailTest() {
        when(applicationService.findApplicationByApplicationId(APPLICATION_ID)).thenReturn(TestServiceData.getCorrectTestApplication());
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String expected = TestServiceData.getCorrectSendSesCodeString();
        simpleMailMessage.setText(expected);

        when(emailClient.sendEmail(any(), any(), any())).thenReturn(simpleMailMessage);
        assertEquals(expected, emailResponseService.sendSesCodeEmail(APPLICATION_ID).getText());
    }

    @Test
    @DisplayName("Корректное составление письма с отказом")
    void correctSendApplicationDeniedEmailTest() {
        when(applicationService.findApplicationByApplicationId(APPLICATION_ID)).thenReturn(TestServiceData.getCorrectTestApplication());
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String expected = TestServiceData.getCorrectSendApplicationDeniedString();
        simpleMailMessage.setText(expected);
        when(emailClient.sendEmail(any(), any(), any())).thenReturn(simpleMailMessage);
        assertEquals(expected, emailResponseService.sendApplicationDeniedEmail(APPLICATION_ID).getText());


    }
}