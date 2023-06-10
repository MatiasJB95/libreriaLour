package com.matiasbadano.libreriaLour.domain.libros;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.matiasbadano.libreriaLour.domain.categoria.CategoriaDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String editorial;
    private double precio;
    private CategoriaDTO categoria;

    public LibroDTO() {
    }

    public LibroDTO(Long id, String titulo, String autor, String editorial, double precio, CategoriaDTO categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.precio = precio;
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

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public static LibroDTO fromLibro(Libro libro) {

        LibroDTO libroSimpleDTO = new LibroDTO();
        libroSimpleDTO.setId(libro.getId());
        libroSimpleDTO.setTitulo(libro.getTitulo());
        libroSimpleDTO.setAutor(libro.getAutor());
        libroSimpleDTO.setEditorial(libro.getEditorial());
        libroSimpleDTO.setPrecio(libro.getPrecio());
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombre(libro.getCategoria().getNombre());
        libroSimpleDTO.setCategoria(categoriaDTO);
        return libroSimpleDTO;
    }
}
