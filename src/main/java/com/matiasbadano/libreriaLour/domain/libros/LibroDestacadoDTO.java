package com.matiasbadano.libreriaLour.domain.libros;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.matiasbadano.libreriaLour.domain.categoria.CategoriaDTO;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibroDestacadoDTO {
    private Integer id;
    private String titulo;
    private String autor;
    private String editorial;
    private Double precio;
    private Boolean destacado;
    private CategoriaDTO categoria;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Boolean getDestacado() {
        return destacado;
    }

    public void setDestacado(Boolean destacado) {
        this.destacado = destacado;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }
}