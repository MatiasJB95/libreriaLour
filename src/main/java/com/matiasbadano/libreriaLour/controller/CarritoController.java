package com.matiasbadano.libreriaLour.controller;
import com.matiasbadano.libreriaLour.domain.carrito.Carrito;
import com.matiasbadano.libreriaLour.domain.carrito.CarritoItem;
import com.matiasbadano.libreriaLour.domain.carrito.CarritoService;
import com.matiasbadano.libreriaLour.domain.libros.Libro;
import com.matiasbadano.libreriaLour.domain.libros.LibroService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carrito")
public class CarritoController {
    private final CarritoService carritoService;

    @Autowired
    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> obtenerCarritoPorId(@PathVariable Long id) {
        Carrito carrito = carritoService.obtenerCarritoPorId(id);
        return new ResponseEntity<>(carrito, HttpStatus.OK);
    }

    @GetMapping("/{id}/productos")
    public ResponseEntity<List<Libro>> obtenerProductosEnCarrito(@PathVariable Long id) {
        Carrito carrito = carritoService.obtenerCarritoPorId(id);
        List<Libro> productosEnCarrito = carritoService.obtenerProductosEnCarrito(carrito);
        return new ResponseEntity<>(productosEnCarrito, HttpStatus.OK);
    }

    @PostMapping("/{id}/agregar")
    public ResponseEntity<Void> agregarAlCarrito(@PathVariable Long id, @RequestBody Map<String, Object> requestBody) {
        Long libroId = Long.parseLong(requestBody.get("libroId").toString());
        int cantidad = Integer.parseInt(requestBody.get("cantidad").toString());
        Carrito carrito = carritoService.obtenerCarritoPorId(id);
        carritoService.agregarAlCarrito(carrito, libroId, cantidad);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<Void> eliminarDelCarrito(@PathVariable Long id, @RequestBody Map<String, Object> requestBody) {
        Long libroId = Long.parseLong(requestBody.get("libroId").toString());
        int cantidad = Integer.parseInt(requestBody.get("cantidad").toString());
        Carrito carrito = carritoService.obtenerCarritoPorId(id);
        carritoService.eliminarDelCarrito(carrito, libroId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @DeleteMapping("/{id}/vaciar")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long id) {
        Carrito carrito = carritoService.obtenerCarritoPorId(id);
        carritoService.vaciarCarrito(carrito);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}