package com.matiasbadano.libreriaLour.domain.usuarios;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.matiasbadano.libreriaLour.domain.carrito.Carrito;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrito_id", referencedColumnName = "id")
    private Carrito carrito;

    private String nombre;

    private String email;

    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles() {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario() {

        this.roles = new HashSet<>();
    }

    // ...

    public Carrito getCarrito()
    {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }


    public void setId(Long id) {
    }
}