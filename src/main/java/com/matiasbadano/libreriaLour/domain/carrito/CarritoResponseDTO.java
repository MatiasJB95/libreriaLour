package com.matiasbadano.libreriaLour.domain.carrito;

import java.util.List;

public class CarritoResponseDTO {
    private List<CarritoItemDTO> productos;
    private Double total;




    public List<CarritoItemDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<CarritoItemDTO> productos) {
        this.productos = productos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}