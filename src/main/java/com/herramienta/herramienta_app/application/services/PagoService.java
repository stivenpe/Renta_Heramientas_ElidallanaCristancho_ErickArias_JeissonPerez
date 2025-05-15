package com.herramienta.herramienta_app.application.services;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.herramienta.herramienta_app.domain.entities.Pago;
import com.herramienta.herramienta_app.infrastructure.repositories.PagoRepository;


import jakarta.annotation.PostConstruct;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    // Inicializa Stripe al iniciar la aplicación
    @PostConstruct
    public void initStripe() {
        Stripe.apiKey = "sk_test_..."; // ⚠️ Usa una variable de entorno en producción
    }

    public void procesarPago(Pago pago) {
        if (pago == null || pago.getMonto() == null) {
            throw new IllegalArgumentException("Pago o monto no pueden ser nulos.");
        }

        // Convertimos el monto a centavos (ej. 20.50 => 2050)
        long montoCentavos = pago.getMonto().multiply(BigDecimal.valueOf(100)).longValue();

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(montoCentavos)
                .setCurrency("usd")
                .build();

        try {
            PaymentIntent paymentIntent = PaymentIntent.create(params);

            // Puedes guardar más detalles del PaymentIntent si lo deseas
            pago.setEstado("Completado");
            pago.setFecha(java.time.LocalDateTime.now());

        } catch (Exception e) {
            pago.setEstado("Fallido");
            pago.setFecha(java.time.LocalDateTime.now());
            throw new RuntimeException("Error al procesar el pago: " + e.getMessage(), e);
        }

        // Guardamos el resultado del pago
        pagoRepository.save(pago);
    }
}
