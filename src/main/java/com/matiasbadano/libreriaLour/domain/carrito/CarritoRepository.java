package com.matiasbadano.libreriaLour.domain.carrito;


import org.springframework.data.jpa.repository.JpaRepository;



public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Carrito findById(long id);
}