package com.coditas.thresholdclinicproject.service;

import com.coditas.thresholdclinicproject.dto.EmailDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmail(
            String to,
            String subject,
            String body
    ) {

        try{
            SimpleMailMessage message =
                    new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(to);

            message.setSubject(subject);

            message.setText(body);

            javaMailSender.send(message);
        }
        catch(Exception e){
            log.error("Failed to send email to {}: {}", to, e.getMessage());
        }

    }


    public String sendSimpleMail(EmailDetails details) {

        try {

            SimpleMailMessage mailMessage =
                    new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);

            return "Mail Sent Successfully";

        } catch (Exception e) {
            return "Error while sending mail";
        }
    }
}
