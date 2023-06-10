package com.matiasbadano.libreriaLour.domain.categoria;

public class CategoriaDTO {
    private long id;
    private String nombre;

    public CategoriaDTO() {
    }

    public CategoriaDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters


    public long getId() {
        return id;
    }

    public void setId(int id) {
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

