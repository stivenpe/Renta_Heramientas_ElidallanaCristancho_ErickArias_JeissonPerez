package com.herramienta.herramienta_app.infrastructure.utils;

import java.util.Properties;

import org.apache.logging.log4j.message.Message;
import org.springframework.boot.rsocket.server.RSocketServer.Transport;

import jakarta.websocket.Session;

public class EmailSender {
    public void sendEmail(String to, String messageContent) {
        String from = "no-reply@herramienta.com";
        String host = "smtp.herramienta.com";  // Configura tu SMTP

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Notificación de Herramienta");
            message.setText(messageContent);

            Transport.send(message);
            System.out.println("Correo enviado exitosamente.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
