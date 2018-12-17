package com.itacademy.jd2.ikarotki.rwmanager.service.impl.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements IMailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;

    @Override
    public void sendEmail(String mail, String Subject, String messageText) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from-email@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
            message.setSubject(Subject);
            message.setText(messageText);

            //Transport.send(message);

            LOGGER.info("mail sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}