
package com.herramienta.herramienta_app.application.services;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.domain.entities.Herramienta;
import com.herramienta.herramienta_app.domain.entities.Notificacion;
import com.herramienta.herramienta_app.infrastructure.repositories.HerramientaRepository;
import com.herramienta.herramienta_app.infrastructure.repositories.NotificacionRepository;
import com.herramienta.herramienta_app.infrastructure.repositories.UsuarioRepository;
import com.herramienta.herramienta_app.infrastructure.repositories.EmailSender;
import com.herramienta.herramienta_app.domain.entities.Usuario;




@Service
public class NotificacionService {
     private final NotificacionRepository notificacionRepository;
    private final HerramientaRepository herramientaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmailSender emailSender;

    public NotificacionService(NotificacionRepository notificacionRepository,
                               HerramientaRepository herramientaRepository,
                               UsuarioRepository usuarioRepository,
                               EmailSender emailSender) {
        this.notificacionRepository = notificacionRepository;
        this.herramientaRepository = herramientaRepository;
        this.usuarioRepository = usuarioRepository;
        this.emailSender = emailSender;
    }

    public void enviarNotificacion(Notificacion notificacion) {
        emailSender.sendEmail(
            notificacion.getUsuario().getEmail(),
            notificacion.getMensaje()
        );
        notificacionRepository.save(notificacion);
    }

    public void enviarRecordatorioDeDevolucion(Long idCliente) {
        Usuario usuario = usuarioRepository.findById(idCliente)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + idCliente));

        List<Herramienta> herramientasPendientes = herramientaRepository
            .findHerramientasNoDevueltasPorUsuario(idCliente);

        if (herramientasPendientes.isEmpty()) {
            return;
        }

        StringBuilder mensaje = new StringBuilder("Tienes herramientas pendientes de devolución:\n");
        for (Herramienta herramienta : herramientasPendientes) {
            mensaje.append("- ")
                   .append(herramienta.getNombre())
                   .append(" (vencía el ")
                   .append(herramienta.getFechaLimiteDevolucion())
                   .append(")\n");
        }

        Notificacion notificacion = new Notificacion();
        notificacion.setUsuario(usuario);
        notificacion.setMensaje(mensaje.toString());
        notificacion.setFecha(LocalDateTime.now());
        notificacion.setLeida(false);
        notificacion.setTipo("Recordatorio de Devolución");

        enviarNotificacion(notificacion);
    }

}
