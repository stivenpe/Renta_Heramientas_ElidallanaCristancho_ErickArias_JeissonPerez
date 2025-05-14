package com.herramienta.herramienta_app.application.services;

import com.herramienta.herramienta_app.infrastructure.repositories.NotificacionRepository;

public class NotificacionService {
     private NotificacionRepository notificacionRepository;
    private EmailSender emailSender;

    public NotificacionService(NotificacionRepository notificacionRepository, EmailSender emailSender) {
        this.notificacionRepository = notificacionRepository;
        this.emailSender = emailSender;
    }

    public void enviarNotificacion(Notificacion notificacion) {
        // Enviar notificación a través de email
        emailSender.sendEmail(notificacion.getUsuario().getEmail(), notificacion.getMensaje());
        notificacionRepository.save(notificacion);
    }

    public void enviarRecordatorioDeDevolucion(Long idCliente) {
        // Lógica para enviar recordatorios de devolución de herramientas
    }

}
