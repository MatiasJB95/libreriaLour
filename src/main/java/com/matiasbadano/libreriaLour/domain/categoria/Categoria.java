package com.matiasbadano.libreriaLour.domain.categoria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.matiasbadano.libreriaLour.domain.libros.Libro;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnoreProperties("categoria")
    private List<Libro> libros;

    public Categoria() {

    }

    @JsonCreator
    public Categoria(@JsonProperty("id") int id) {
        this.id = id;
    }

    public int getId() {
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

    public List<Libro> getLibros() {
        return libros;
    }
}