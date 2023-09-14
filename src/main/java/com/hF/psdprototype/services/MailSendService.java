package com.hF.psdprototype.services;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailSendService {


    private final String from = "ofbrick121@gmail.com";
    String smtpPort = "465";

    String smtpHost = "smtp.gmail.com";

    Properties properties = new Properties();

    {
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
    }

    Session session = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, "qzxhxzfbtplttsat");
        }
    });

    public void send(String recipientGmail, String text, String subject) throws MessagingException {
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientGmail));
        message.setSubject(subject);
        message.setText(text);
        Transport.send(message);
    }

}
