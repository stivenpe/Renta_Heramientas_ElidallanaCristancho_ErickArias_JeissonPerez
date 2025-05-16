
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
@RequiredArgsConstructor
public class NotificacionService {
    private final NotificacionRepository notificacionRepository;
    private final UsuarioRepository usuarioRepository;
    
    public Notificacion crearNotificacion(Long usuarioId, String titulo, String mensaje) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
            
        Notificacion notificacion = new Notificacion();
        notificacion.setTitulo(titulo);
        notificacion.setMensaje(mensaje);
        notificacion.setUsuario(usuario);
        notificacion.setFechaCreacion(LocalDateTime.now());
        
        return notificacionRepository.save(notificacion);
    }
    
    public List<Notificacion> obtenerNotificacionesNoLeidas(Long usuarioId) {
        return notificacionRepository.findByUsuarioIdAndLeidaFalse(usuarioId);
    }
    
    public void marcarComoLeida(Long notificacionId) {
        Notificacion notificacion = notificacionRepository.findById(notificacionId)
            .orElseThrow(() -> new IllegalArgumentException("Notificación no encontrada"));
            
        notificacion.setLeida(true);
        notificacionRepository.save(notificacion);
    }
}