package com.matiasbadano.libreriaLour.domain.carrito;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.matiasbadano.libreriaLour.domain.categoria.CategoriaDTO;
import com.matiasbadano.libreriaLour.domain.libros.Libro;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarritoDTO {
    private Long id;
    private String titulo;
    private String autor;

    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    private String editorial;
    private Double precio;
    private CategoriaDTO categoria;
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Double getPrecio() {
        return precio;
    }


    public void setPrecio(Double precio) {
        this.precio = precio;
    }


    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public static CarritoDTO fromLibro(Libro libro) {
        CarritoDTO libroCarritoDTO = new CarritoDTO();
        libroCarritoDTO.setId(libro.getId());
        libroCarritoDTO.setTitulo(libro.getTitulo());
        libroCarritoDTO.setAutor(libro.getAutor());
        libroCarritoDTO.setEditorial(libro.getEditorial());
        libroCarritoDTO.setPrecio(libro.getPrecio());
        libroCarritoDTO.setCategoria(CategoriaDTO.fromCategoria(libro.getCategoria()));
        return libroCarritoDTO;
    }
}