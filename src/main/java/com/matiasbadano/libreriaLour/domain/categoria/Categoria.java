package com.matiasbadano.libreriaLour.domain.categoria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matiasbadano.libreriaLour.domain.libros.Libro;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnoreProperties("categoria")
    private List<Libro> libros;

    public Categoria() {

    }
    public Categoria(String nombre) {

        this.nombre = nombre;
    }


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



    //bucle infinito
    public List<Libro> getLibros() {
        return libros;
    }

}