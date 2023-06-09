package com.matiasbadano.libreriaLour.infra.errores;
public class CarritoNotFoundException extends RuntimeException {
    public CarritoNotFoundException(String mensaje) {
        super(mensaje);
    }
}