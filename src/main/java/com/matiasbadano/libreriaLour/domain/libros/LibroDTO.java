package com.matiasbadano.libreriaLour.domain.libros;

import com.matiasbadano.libreriaLour.domain.categoria.CategoriaDTO;

public class LibroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String editorial;
    private double precio;
    private boolean destacado;
    private CategoriaDTO categoria;

    public LibroDTO() {
    }

    public LibroDTO(Long id, String titulo, String autor, String editorial, double precio, boolean destacado, CategoriaDTO categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.precio = precio;
        this.destacado = destacado;
        this.categoria = categoria;
    }

    // Getters y setters

    public Long getId() {
        return id;
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

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDestacado() {
        return destacado;
    }

    public void setDestacado(boolean destacado) {
        this.destacado = destacado;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }
}