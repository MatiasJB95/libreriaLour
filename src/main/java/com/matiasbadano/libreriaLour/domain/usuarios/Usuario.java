package com.matiasbadano.libreriaLour.domain.usuarios;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String email;

    private String password;

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

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

}