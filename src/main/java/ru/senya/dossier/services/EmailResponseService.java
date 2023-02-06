package ru.senya.dossier.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ru.senya.dossier.clients.EmailClient;
import ru.senya.dossier.entity.dto.LoanOfferDTO;
import ru.senya.dossier.entity.dto.PaymentScheduleElement;
import ru.senya.dossier.entity.enums.ApplicationStatus;
import ru.senya.dossier.entity.models.Application;
import ru.senya.dossier.entity.models.Credit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailResponseService {

    private final EmailClient emailClient;
    private final ApplicationService applicationService;
    private final LoanOfferService loanOfferService;
    private final PaymentScheduleService paymentScheduleService;

    public SimpleMailMessage sendFinishRegistrationEmail(Long applicationId) {
        Application application = applicationService.findApplicationByApplicationId(applicationId);
        LoanOfferDTO loanOfferDTO = loanOfferService.convertToLoanOfferDTO(application.getAppliedOffer());

        return emailClient.sendEmail(application.getClientId().getEmail(), "Завершите оформление", "Подтвердите выбор заявки: \n"
                + "\nЗапрашиваемая сумма: " + loanOfferDTO.getRequestedAmount()
                + "\nИтоговая сумма: " + loanOfferDTO.getTotalAmount()
                + "\nСрок: " + loanOfferDTO.getTerm() + " месяцев"
                + "\nЕжемесячный платеж: " + loanOfferDTO.getMonthlyPayment()
                + "\nВключена страховка: " + loanOfferDTO.getIsInsuranceEnabled()
                + "\nЗарплатный клиент: " + loanOfferDTO.getIsSalaryClient());
    }

    public SimpleMailMessage sendCreateDocumentsEmail(Long applicationId) {
        Application application = applicationService.findApplicationByApplicationId(applicationId);
        Credit credit = application.getCreditId();

        return emailClient.sendEmail(application.getClientId().getEmail(), "Перейти к оформлению документов", "Перейти к оформлению документов с условиями: \n"
                + "\nСумма: " + credit.getAmount()
                + "\nСрок: " + credit.getTerm() + " месяцев"
                + "\nЕжемесячный платеж: " + credit.getMonthlyPayment()
                + "\nСтавка: " + credit.getRate()
                + "\nПСК: " + credit.getPsk()
                + "\nРасписание платежей: " + paymentScheduleInfo(paymentScheduleService.getPaymentScheduleElementList(credit.getPaymentSchedule()))
                + "\nВключена страховка: " + credit.getInsuranceEnable()
                + "\nЗарплатный клиент: " + credit.getSalaryClient());
    }

    private String paymentScheduleInfo(List<PaymentScheduleElement> paymentScheduleElementList) {
        StringBuilder paymentScheduleElements = new StringBuilder();
        for (int i = 0; i < paymentScheduleElementList.size(); i++) {
            paymentScheduleElements.append("\nНомер платежа: ");
            paymentScheduleElements.append(i + 1);
            paymentScheduleElements.append("\nДата платежа: ");
            paymentScheduleElements.append(paymentScheduleElementList.get(i).getDate());
            paymentScheduleElements.append("\nОбщий платеж: ");
            paymentScheduleElements.append(paymentScheduleElementList.get(i).getTotalPayment());
            paymentScheduleElements.append("\nПлатеж без долга: ");
            paymentScheduleElements.append(paymentScheduleElementList.get(i).getInterestPayment());
            paymentScheduleElements.append("\nПлатеж по долгу: ");
            paymentScheduleElements.append(paymentScheduleElementList.get(i).getDebtPayment());
            paymentScheduleElements.append("\nОстаток суммы: ");
            paymentScheduleElements.append(paymentScheduleElementList.get(i).getRemainingDebt());
            paymentScheduleElements.append("\n");
        }
        return paymentScheduleElements.toString();
    }

    public SimpleMailMessage sendSendDocumentsEmail(Long applicationId) {
        Application application = applicationService.findApplicationByApplicationId(applicationId);

        return emailClient.sendEmail(application.getClientId().getEmail(), "Документы сформированы",
                "Документы по кредитной заявке оформлены. Для завершения, отправьте запрос на подписание");
    }

    public SimpleMailMessage sendSesCodeEmail(Long applicationId) {
        Application application = applicationService.findApplicationByApplicationId(applicationId);
        return emailClient.sendEmail(application.getClientId().getEmail(), "Подписание документов",
                "\nСсылка на подписание: google.com"
                + "\nВаш ПЭП-код: " + ((int)(Math.random()*9000)+1000));
    }

    public SimpleMailMessage sendApplicationDeniedEmail(Long applicationId) {
        Application application = applicationService.findApplicationByApplicationId(applicationId);
        application.setStatus(ApplicationStatus.CC_DENIED);
        return emailClient.sendEmail(application.getClientId().getEmail(), "В кредите отказано",
                "Банк принял решение отказать в кредите");
    }

}
