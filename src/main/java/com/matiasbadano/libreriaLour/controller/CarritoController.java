package com.matiasbadano.libreriaLour.controller;
import com.matiasbadano.libreriaLour.domain.carrito.*;
import com.matiasbadano.libreriaLour.domain.libros.Libro;
import com.matiasbadano.libreriaLour.domain.libros.LibroRepository;
import com.matiasbadano.libreriaLour.domain.libros.LibroService;
import com.matiasbadano.libreriaLour.infra.errores.LibroNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carrito")
public class CarritoController {
    private final CarritoService carritoService;
    private final LibroService libroService;
    private final LibroRepository libroRepository;
    private CarritoRepository carritoRepository;
    private CarritoItemRepository carritoItemRepository;

    @Autowired
    public CarritoController(CarritoService carritoService, LibroService libroService, LibroRepository libroRepository, CarritoRepository carritoRepository) {
        this.carritoService = carritoService;
        this.libroService = libroService;
        this.libroRepository = libroRepository;
        this.carritoRepository = carritoRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> obtenerCarritoPorId(@PathVariable Long id) {
        Carrito carrito = carritoService.obtenerCarritoPorId(id);
        return new ResponseEntity<>(carrito, HttpStatus.OK);
    }

    @GetMapping("/{id}/productos")
    public ResponseEntity<CarritoResponseDTO> obtenerProductosEnCarrito(@PathVariable Long id) {
        Carrito carrito = carritoService.obtenerCarritoPorId(id);
        List<CarritoItemDTO> productosEnCarrito = carritoService.obtenerProductosEnCarrito(carrito);

        double total = productosEnCarrito.stream()
                .mapToDouble(carritoItemDTO -> carritoItemDTO.getPrecio() * carritoItemDTO.getCantidad())
                .sum();
        CarritoResponseDTO responseDTO = new CarritoResponseDTO();
        responseDTO.setProductos(productosEnCarrito);
        responseDTO.setTotal(total);

        return ResponseEntity.ok(responseDTO);
    }

    private CarritoDTO mapToCarritoDTO(Libro libro) {
        CarritoDTO carritoDTO = new CarritoDTO();
        carritoDTO.setId(libro.getId());
        carritoDTO.setTitulo(libro.getTitulo());
        carritoDTO.setAutor(libro.getAutor());
        carritoDTO.setEditorial(libro.getEditorial());
        carritoDTO.setPrecio(libro.getPrecio());
        carritoDTO.setCategoria(libro.getCategoriaDTO());
        return carritoDTO;
    }

    private CarritoItemDTO mapToCarritoItemDTO(Libro libro) {
        CarritoItemDTO carritoItemDTO = new CarritoItemDTO();
        carritoItemDTO.setId(libro.getId());
        carritoItemDTO.setTitulo(libro.getTitulo());
        carritoItemDTO.setAutor(libro.getAutor());
        carritoItemDTO.setEditorial(libro.getEditorial());
        carritoItemDTO.setPrecio(libro.getPrecio());
        carritoItemDTO.setCategoria(libro.getCategoriaDTO());
        return carritoItemDTO;
    }

    @PostMapping("/{id}/agregar")
    public ResponseEntity<Void> agregarAlCarrito(@PathVariable Long id, @RequestBody Map<String, Object> requestBody) {
        Long libroId = Long.parseLong(requestBody.get("libroId").toString());
        Integer cantidad = requestBody.get("cantidad") != null ? Integer.parseInt(requestBody.get("cantidad").toString()) : null;
        Carrito carrito = carritoService.obtenerCarritoPorId(id);
        carritoService.agregarAlCarrito(carrito, libroId, cantidad);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<Void> eliminarDelCarrito(@PathVariable Long id, @RequestBody Map<String, Object> requestBody) {
        Long libroId = Long.parseLong(requestBody.get("libroId").toString());
        int cantidad = Integer.parseInt(requestBody.get("cantidad").toString());
        Carrito carrito = carritoService.obtenerCarritoPorId(id);
        carritoService.eliminarDelCarrito(carrito, libroId, cantidad);
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