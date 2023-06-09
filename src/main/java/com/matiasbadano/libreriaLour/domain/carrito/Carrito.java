package com.matiasbadano.libreriaLour.domain.carrito;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.matiasbadano.libreriaLour.domain.libros.Libro;
import com.matiasbadano.libreriaLour.domain.usuarios.Usuario;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "carrito")
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonManagedReference
    @OneToOne(mappedBy = "carrito", cascade = CascadeType.ALL)
    private Usuario usuario;
    @JsonManagedReference
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private List<CarritoItem> carritoItems;

    public Carrito() {
        this.carritoItems = new ArrayList<>();
    }

    // Resto de los getters y setters

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<CarritoItem> getCarritoItems() {
        return carritoItems;
    }

    public void setCarritoItems(List<CarritoItem> carritoItems) {
        this.carritoItems = carritoItems;
    }

    public void agregarCarritoItem(CarritoItem carritoItem) {
        carritoItems.add(carritoItem);
        carritoItem.setCarrito(this);
    }

    public void eliminarCarritoItem(CarritoItem carritoItem) {
        carritoItems.remove(carritoItem);
        carritoItem.setCarrito(null);
    }
}