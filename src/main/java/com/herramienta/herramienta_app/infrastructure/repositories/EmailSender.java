package com.herramienta.herramienta_app.infrastructure.repositories;

public interface EmailSender {
    void sendEmail(String to, String mensaje);
}
