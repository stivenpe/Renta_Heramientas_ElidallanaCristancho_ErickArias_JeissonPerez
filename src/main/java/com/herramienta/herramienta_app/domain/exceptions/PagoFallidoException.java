package com.herramienta.herramienta_app.domain.exceptions;

public class PagoFallidoException extends RuntimeException {
    public PagoFallidoException(String message) {
        super(message);
    }
}