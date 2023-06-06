package com.matiasbadano.libreriaLour.controller;

import com.matiasbadano.libreriaLour.domain.carrito.Carrito;
import com.matiasbadano.libreriaLour.domain.carrito.CarritoRepository;
import com.matiasbadano.libreriaLour.domain.libros.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;

    public Carrito agregarLibroAlCarrito(Carrito carrito, Libro libro) {
        List<Libro> libros = carrito.getLibros();
        libros.add(libro);
        return carritoRepository.save(carrito);
    }

    public Carrito eliminarLibroDelCarrito(Carrito carrito, Long libroId) {
        List<Libro> libros = carrito.getLibros();
        libros.removeIf(libro -> libro.getId().equals(libroId));
        return carritoRepository.save(carrito);
    }

}

