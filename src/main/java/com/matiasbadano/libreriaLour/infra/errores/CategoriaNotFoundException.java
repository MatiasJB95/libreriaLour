package com.matiasbadano.libreriaLour.infra.errores;

public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException(String mensaje) {
        super(mensaje);
    }
}
