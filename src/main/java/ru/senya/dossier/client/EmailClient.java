package ru.senya.dossier.client;

import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailClient {

    private final JavaMailSender mailSender;
    Logger logger = LoggerFactory.getLogger(EmailClient.class);

    public SimpleMailMessage sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("arsshvets@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

        logger.trace("Email was successfully sent");

        return message;
    }

}
