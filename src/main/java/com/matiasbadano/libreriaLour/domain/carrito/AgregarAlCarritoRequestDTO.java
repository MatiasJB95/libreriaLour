package com.matiasbadano.libreriaLour.domain.carrito;

public class AgregarAlCarritoRequestDTO {
    private Long libroId;
    private int cantidad;

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
