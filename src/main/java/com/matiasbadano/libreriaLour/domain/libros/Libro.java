package com.matiasbadano.libreriaLour.domain.libros;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.matiasbadano.libreriaLour.domain.carrito.CarritoItem;
import com.matiasbadano.libreriaLour.domain.categoria.Categoria;
import com.matiasbadano.libreriaLour.domain.categoria.CategoriaDTO;
import com.matiasbadano.libreriaLour.domain.categoria.CategoriaRepository;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String editorial;
    private double precio;
    private boolean destacado;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @OneToMany(mappedBy = "libro")
    @JsonIgnore
    private List<CarritoItem> carritoItems;
    public Libro() {
    }

    public Libro(String titulo, String autor, String editorial, Categoria categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.categoria = categoria;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public List<CarritoItem> getCarritoItems() {
        return carritoItems;
    }

    public void setCarritoItems(List<CarritoItem> carritoItems) {
        this.carritoItems = carritoItems;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    public boolean isDestacado() {
        return destacado;
    }

    public void setDestacado(boolean destacado) {
        this.destacado = destacado;
    }

    public CategoriaDTO getCategoriaDTO() {
        Categoria categoria = this.categoria;
        if (categoria == null) {
            return null;
        }

        return new CategoriaDTO(categoria.getId(), categoria.getNombre());
    }
}