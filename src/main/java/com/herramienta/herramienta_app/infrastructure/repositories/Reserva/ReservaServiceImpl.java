package com.herramienta.herramienta_app.infrastructure.repositories.Reserva;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.application.services.ReservaService;
import com.herramienta.herramienta_app.domain.entities.Reserva;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    @Override
    public Optional<Reserva> findById(Long id) {
        return reservaRepository.findById(id);
    }

    @Override
    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public void deleteById(Long id) {
        reservaRepository.deleteById(id);
    }

    public List<Reserva> listarPorCliente(Long clienteId) {
        return reservaRepository.findByClienteId(clienteId);
    }

    
}