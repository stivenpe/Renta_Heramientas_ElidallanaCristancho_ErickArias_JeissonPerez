package com.herramienta.herramienta_app.application;

import java.math.BigDecimal;

import com.herramienta.herramienta_app.domain.entities.Pago;
import com.herramienta.herramienta_app.infrastructure.repositories.PagoRepository;

public class PagoService {
     private PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
        Stripe.apiKey = "sk_test_...";  // Clave secreta de Stripe
    }

    public void procesarPago(Pago pago) {
        // Crear un PaymentIntent en Stripe
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(pago.getMonto().multiply(BigDecimal.valueOf(100)).longValue())  // Stripe requiere la cantidad en centavos
                .setCurrency("usd")
                .build();

        try {
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            pago.setEstado("Completado");
            pagoRepository.save(pago);
        } catch (Exception e) {
            pago.setEstado("Fallido");
            pagoRepository.save(pago);
            throw new RuntimeException("Error al procesar el pago");
        }
    }

}
