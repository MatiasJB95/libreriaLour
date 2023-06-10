package com.matiasbadano.libreriaLour.domain.categoria;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriaDTO {

    private Long id;
    private String nombre;

    public CategoriaDTO() {
    }

    public CategoriaDTO(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static CategoriaDTO fromCategoria(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getNombre());
    }
}