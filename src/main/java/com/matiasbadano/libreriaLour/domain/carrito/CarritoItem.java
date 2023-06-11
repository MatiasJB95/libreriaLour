package com.matiasbadano.libreriaLour.domain.carrito;

import com.matiasbadano.libreriaLour.domain.libros.Libro;
import jakarta.persistence.*;

import java.util.Optional;
@Entity
@Table(name = "carrito_item")
public class CarritoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;

    private int cantidad;

    public CarritoItem() {
    }

    public CarritoItem(Carrito carrito, Libro libro, int cantidad) {
        this.carrito = carrito;
        this.libro = libro;
        this.cantidad = cantidad;
    }

    public CarritoItem(Optional<Libro> libro, int cantidad) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}