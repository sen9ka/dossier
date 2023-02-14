package ru.senya.dossier.service;

import lombok.RequiredArgsConstructor;
import ru.senya.dossier.entity.dto.LoanOfferDTO;
import ru.senya.dossier.entity.dto.PaymentScheduleElement;
import ru.senya.dossier.entity.enums.*;
import ru.senya.dossier.entity.field.Employment;
import ru.senya.dossier.entity.field.Passport;
import ru.senya.dossier.entity.model.Application;
import ru.senya.dossier.entity.model.Client;
import ru.senya.dossier.entity.model.Credit;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class TestServiceData {

    public static PaymentScheduleService paymentScheduleService() {
        return new PaymentScheduleService();
    }

    public static String getUnmappableString() {
        return "WrongString";
    }

    public static String getCorrectEmailMessageString() {
        return "{ \"address\": \"ars-shvec@yandex.ru\", \"theme\": \"CC_DENIED\", \"applicationId\": 85 }";
    }

    public static Passport getCorrectTestPassport() {

        return Passport.builder()
                .passportId(103)
                .series("1414")
                .number("142141")
                .issueBranch("SPB")
                .issueDate(LocalDate.of(2023, 10, 10))
                .build();
    }

    public static Employment getCorrectTestEmployment() {
        return Employment.builder()
                .employmentId(103)
                .status(EmploymentStatus.SELF_EMPLOYED)
                .employerINN("15116161")
                .salary(BigDecimal.valueOf(400000))
                .position(EmploymentPosition.TOP_MANAGER)
                .workExperienceTotal(500)
                .workExperienceCurrent(200)
                .build();
    }

    public static Client getCorrectTestClient() {
        return Client.builder()
                .clientId(104L)
                .firstName("Arseniy")
                .lastName("Shvets")
                .middleName("Kostya")
                .birthDate(LocalDate.of(1999, 3, 22))
                .email("ars=shvec@yandex.ru")
                .gender(Gender.MALE)
                .maritalStatus(MaritalStatus.MARRIED)
                .dependentAmount(BigDecimal.valueOf(100000))
                .passportId(getCorrectTestPassport())
                .employmentId(getCorrectTestEmployment())
                .account("myAccount")
                .build();
    }

    public static Application getCorrectTestApplication() {
        return Application.builder()
                .applicationId(87L)
                .clientId(getCorrectTestClient())
                .creditId(new Credit())
                .status(ApplicationStatus.PREAPPROVAL)
                .creationDate(LocalDateTime.of(2023, 2,5, 16, 49))
                .appliedOffer("{\"applicationId\":87,\"requestedAmount\":100000,\"totalAmount\":119600.0,\"term\":12,\"monthlyPayment\":9966.7,\"rate\":9.6,\"isInsuranceEnabled\":true,\"isSalaryClient\":true}")
                .signDate(LocalDate.of(2023, 2, 5))
                .sesCode(1)
                .statusHistory("[StatusHistory(status=CREATED, time=2023-02-05T16:49:09.726425500, changeType=AUTOMATIC), StatusHistory(status=UPDATED, time=2023-02-05T16:49:15.438003900, changeType=AUTOMATIC)]")
                .build();
    }

    public static LoanOfferDTO getCorrectTestLoanOfferDTO() {
        return LoanOfferDTO.builder()
                .applicationId(87L)
                .requestedAmount(BigDecimal.valueOf(100000))
                .totalAmount(BigDecimal.valueOf(119600.0))
                .term(12)
                .monthlyPayment(BigDecimal.valueOf(9966.7))
                .isInsuranceEnabled(true)
                .isSalaryClient(true)
                .build();
    }

    public static String getLoanOfferDTOCorrectString() {
        return "{\"applicationId\":87,\"requestedAmount\":100000,\"totalAmount\":119600.0,\"term\":12,\"monthlyPayment\":9966.7,\"rate\":9.6,\"isInsuranceEnabled\":true,\"isSalaryClient\":true}";
    }

    public static List<PaymentScheduleElement> getPaymentScheduleElementList() {
        return paymentScheduleService().getPaymentScheduleElementList("[{\"number\":1,\"date\":[2023,3,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":131450.367},{\"number\":2,\"date\":[2023,4,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":119500.334},{\"number\":3,\"date\":[2023,5,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":107550.301},{\"number\":4,\"date\":[2023,6,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":95600.268},{\"number\":5,\"date\":[2023,7,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":83650.235},{\"number\":6,\"date\":[2023,8,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":71700.202},{\"number\":7,\"date\":[2023,9,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":59750.169},{\"number\":8,\"date\":[2023,10,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":47800.136},{\"number\":9,\"date\":[2023,11,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":35850.103},{\"number\":10,\"date\":[2023,12,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":23900.070},{\"number\":11,\"date\":[2024,1,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":11950.037},{\"number\":12,\"date\":[2024,2,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":0.004}]");
    }

    public static String getCorrectFinishRegistrationString() {
        return "Подтвердите выбор заявки:\n" +
                "\n" +
                "Запрашиваемая сумма: 100000\n" +
                "Итоговая сумма: 119600.0\n" +
                "Срок: 12 месяцев\n" +
                "Ежемесячный платеж: 9966.7\n" +
                "Включена страховка: true\n" +
                "Зарплатный клиент: true";
    }

    public static String getCorrectCreateDocumentsString() {
        return "Перейти к оформлению документов с условиями:\n" +
                "\n" +
                "Сумма: 119600.0\n" +
                "Срок: 12 месяцев\n" +
                "Ежемесячный платеж: 11950.033\n" +
                "Ставка: 9.0\n" +
                "ПСК: 19.90\n" +
                "Расписание платежей:\n" +
                "Номер платежа: 1\n" +
                "Дата платежа: 2023-03-05\n" +
                "Общий платеж: 11950.033\n" +
                "Платеж без долга: 10755.02970\n" +
                "Платеж по долгу: 1195.00330\n" +
                "Остаток суммы: 131450.367\n" +
                "\n" +
                "Номер платежа: 2\n" +
                "Дата платежа: 2023-04-05\n" +
                "Общий платеж: 11950.033\n" +
                "Платеж без долга: 10755.02970\n" +
                "Платеж по долгу: 1195.00330\n" +
                "Остаток суммы: 119500.334\n" +
                "\n" +
                "Номер платежа: 3\n" +
                "Дата платежа: 2023-05-05\n" +
                "Общий платеж: 11950.033\n" +
                "Платеж без долга: 10755.02970\n" +
                "Платеж по долгу: 1195.00330\n" +
                "Остаток суммы: 107550.301\n" +
                "\n" +
                "Номер платежа: 4\n" +
                "Дата платежа: 2023-06-05\n" +
                "Общий платеж: 11950.033\n" +
                "Платеж без долга: 10755.02970\n" +
                "Платеж по долгу: 1195.00330\n" +
                "Остаток суммы: 95600.268\n" +
                "\n" +
                "Номер платежа: 5\n" +
                "Дата платежа: 2023-07-05\n" +
                "Общий платеж: 11950.033\n" +
                "Платеж без долга: 10755.02970\n" +
                "Платеж по долгу: 1195.00330\n" +
                "Остаток суммы: 83650.235\n" +
                "\n" +
                "Номер платежа: 6\n" +
                "Дата платежа: 2023-08-05\n" +
                "Общий платеж: 11950.033\n" +
                "Платеж без долга: 10755.02970\n" +
                "Платеж по долгу: 1195.00330\n" +
                "Остаток суммы: 71700.202\n" +
                "\n" +
                "Номер платежа: 7\n" +
                "Дата платежа: 2023-09-05\n" +
                "Общий платеж: 11950.033\n" +
                "Платеж без долга: 10755.02970\n" +
                "Платеж по долгу: 1195.00330\n" +
                "Остаток суммы: 59750.169\n" +
                "\n" +
                "Номер платежа: 8\n" +
                "Дата платежа: 2023-10-05\n" +
                "Общий платеж: 11950.033\n" +
                "Платеж без долга: 10755.02970\n" +
                "Платеж по долгу: 1195.00330\n" +
                "Остаток суммы: 47800.136\n" +
                "\n" +
                "Номер платежа: 9\n" +
                "Дата платежа: 2023-11-05\n" +
                "Общий платеж: 11950.033\n" +
                "Платеж без долга: 10755.02970\n" +
                "Платеж по долгу: 1195.00330\n" +
                "Остаток суммы: 35850.103\n" +
                "\n" +
                "Номер платежа: 10\n" +
                "Дата платежа: 2023-12-05\n" +
                "Общий платеж: 11950.033\n" +
                "Платеж без долга: 10755.02970\n" +
                "Платеж по долгу: 1195.00330\n" +
                "Остаток суммы: 23900.070\n" +
                "\n" +
                "Номер платежа: 11\n" +
                "Дата платежа: 2024-01-05\n" +
                "Общий платеж: 11950.033\n" +
                "Платеж без долга: 10755.02970\n" +
                "Платеж по долгу: 1195.00330\n" +
                "Остаток суммы: 11950.037\n" +
                "\n" +
                "Номер платежа: 12\n" +
                "Дата платежа: 2024-02-05\n" +
                "Общий платеж: 11950.033\n" +
                "Платеж без долга: 10755.02970\n" +
                "Платеж по долгу: 1195.00330\n" +
                "Остаток суммы: 0.004\n" +
                "\n" +
                "Включена страховка: true\n" +
                "Зарплатный клиент: true";
    }

    public static String getCorrectSendDocumentsString() {
        return "Документы по кредитной заявке оформлены. Для завершения, отправьте запрос на подписание";
    }

    public static String getCorrectSendSesCodeString() {
        return "\nСсылка на подписание: google.com";
    }

    public static String getCorrectSendApplicationDeniedString() {
        return "Банк принял решение отказать в кредите";
    }

    public static String getPaymentScheduleString() {
        return "[{\"number\":1,\"date\":[2023,3,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":131450.367},{\"number\":2,\"date\":[2023,4,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":119500.334},{\"number\":3,\"date\":[2023,5,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":107550.301},{\"number\":4,\"date\":[2023,6,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":95600.268},{\"number\":5,\"date\":[2023,7,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":83650.235},{\"number\":6,\"date\":[2023,8,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":71700.202},{\"number\":7,\"date\":[2023,9,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":59750.169},{\"number\":8,\"date\":[2023,10,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":47800.136},{\"number\":9,\"date\":[2023,11,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":35850.103},{\"number\":10,\"date\":[2023,12,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":23900.070},{\"number\":11,\"date\":[2024,1,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":11950.037},{\"number\":12,\"date\":[2024,2,5],\"totalPayment\":11950.033,\"interestPayment\":10755.02970,\"debtPayment\":1195.00330,\"remainingDebt\":0.004}]";
    }
}
