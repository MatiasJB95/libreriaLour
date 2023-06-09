package com.matiasbadano.libreriaLour.infra.errores;

public class LibroNotFoundException extends RuntimeException {
    public LibroNotFoundException(String mensaje) {
        super(mensaje);
    }
}
