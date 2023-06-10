package com.matiasbadano.libreriaLour.domain.carrito;

import java.util.List;

public class CarritoResponseDTO {
    private List<CarritoDTO> productos;
    private Double total;

    public List<CarritoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<CarritoDTO> productos) {
        this.productos = productos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}