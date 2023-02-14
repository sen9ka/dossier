package ru.senya.dossier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ru.senya.dossier.client.EmailClient;
import ru.senya.dossier.entity.dto.LoanOfferDTO;
import ru.senya.dossier.entity.dto.PaymentScheduleElement;
import ru.senya.dossier.entity.enums.ApplicationStatus;
import ru.senya.dossier.entity.model.Application;
import ru.senya.dossier.entity.model.Credit;
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

        return emailClient.sendEmail(application.getClientId().getEmail(), Constant.COMPLETE_CLEARANCE_SUBJECT, Constant.CONFIRM_SELECTION
                + Constant.REQUESTED_AMOUNT + loanOfferDTO.getRequestedAmount()
                + Constant.TOTAL_AMOUNT + loanOfferDTO.getTotalAmount()
                + Constant.TERM + loanOfferDTO.getTerm() + Constant.MONTHS
                + Constant.MONTHLY_PAYMENT + loanOfferDTO.getMonthlyPayment()
                + Constant.INSURANCE_ENABLED + loanOfferDTO.getIsInsuranceEnabled()
                + Constant.SALARY_CLIENT + loanOfferDTO.getIsSalaryClient());
    }

    public SimpleMailMessage sendCreateDocumentsEmail(Long applicationId) {
        Application application = applicationService.findApplicationByApplicationId(applicationId);
        Credit credit = application.getCreditId();

        return emailClient.sendEmail(application.getClientId().getEmail(), Constant.GO_TO_PAPERWORK, Constant.GO_TO_PAPERWORK_WITH_CONDITIONS
                + Constant.AMOUNT + credit.getAmount()
                + Constant.TERM + credit.getTerm() + Constant.MONTHS
                + Constant.MONTHLY_PAYMENT + credit.getMonthlyPayment()
                + Constant.RATE + credit.getRate()
                + Constant.PSK + credit.getPsk()
                + Constant.PAYMENT_SCHEDULE + paymentScheduleInfo(paymentScheduleService.getPaymentScheduleElementList(credit.getPaymentSchedule()))
                + Constant.INSURANCE_ENABLED + credit.getInsuranceEnable()
                + Constant.SALARY_CLIENT + credit.getSalaryClient());
    }

    private String paymentScheduleInfo(List<PaymentScheduleElement> paymentScheduleElementList) {
        StringBuilder paymentScheduleElements = new StringBuilder();
        for (int i = 0; i < paymentScheduleElementList.size(); i++) {
            paymentScheduleElements.append(Constant.PAYMENT_NUMBER);
            paymentScheduleElements.append(i + 1);
            paymentScheduleElements.append(Constant.PAYMENT_DATE);
            paymentScheduleElements.append(paymentScheduleElementList.get(i).getDate());
            paymentScheduleElements.append(Constant.TOTAL_PAYMENT);
            paymentScheduleElements.append(paymentScheduleElementList.get(i).getTotalPayment());
            paymentScheduleElements.append(Constant.INTEREST_PAYMENT);
            paymentScheduleElements.append(paymentScheduleElementList.get(i).getInterestPayment());
            paymentScheduleElements.append(Constant.DEBT_PAYMENT);
            paymentScheduleElements.append(paymentScheduleElementList.get(i).getDebtPayment());
            paymentScheduleElements.append(Constant.REMAINING_DEBT);
            paymentScheduleElements.append(paymentScheduleElementList.get(i).getRemainingDebt());
            paymentScheduleElements.append(Constant.NEW_STRING);
        }
        return paymentScheduleElements.toString();
    }

    public SimpleMailMessage sendSendDocumentsEmail(Long applicationId) {
        Application application = applicationService.findApplicationByApplicationId(applicationId);

        return emailClient.sendEmail(application.getClientId().getEmail(), Constant.DOCUMENTS_GENERATED_SUBJECT,
                Constant.DOCUMENTS_GENERATED_BODY);
    }

    public SimpleMailMessage sendSesCodeEmail(Long applicationId) {
        Application application = applicationService.findApplicationByApplicationId(applicationId);
        return emailClient.sendEmail(application.getClientId().getEmail(), Constant.SEND_SES_SUBJECT,
                Constant.SIGN_URL + Constant.CURRENT_URL + Constant.YOUR_SES_CODE + ((int)(Math.random()*9000)+1000));
    }

    public SimpleMailMessage sendApplicationDeniedEmail(Long applicationId) {
        Application application = applicationService.findApplicationByApplicationId(applicationId);
        application.setStatus(ApplicationStatus.CC_DENIED);
        return emailClient.sendEmail(application.getClientId().getEmail(), Constant.APPLICATION_DENIED_SUBJECT,
                Constant.APPLICATION_DENIED_BODY);
    }

}
