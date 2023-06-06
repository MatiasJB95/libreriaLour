package com.matiasbadano.libreriaLour.controller;
import com.matiasbadano.libreriaLour.domain.categoria.Categoria;
import com.matiasbadano.libreriaLour.domain.categoria.CategoriaRepository;
import com.matiasbadano.libreriaLour.domain.libros.Libro;
import com.matiasbadano.libreriaLour.domain.libros.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodosLosLibros() {
        List<Libro> libros = libroRepository.findAll();
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable("id") Long id) {
        Libro libro = libroRepository.findById(id).orElse(null);
        if (libro != null) {
            return new ResponseEntity<>(libro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroRepository.save(libro);
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable("id") Long id, @RequestBody Libro libroActualizado) {
        Libro libroExistente = libroRepository.findById(id).orElse(null);
        if (libroExistente != null) {
            libroExistente.setTitulo(libroActualizado.getTitulo());
            libroExistente.setAutor(libroActualizado.getAutor());
            libroExistente.setEditorial(libroActualizado.getEditorial());
            // Actualiza otros atributos según sea necesario

            Libro libroActualizadoEntidad = libroRepository.save(libroExistente);
            return new ResponseEntity<>(libroActualizadoEntidad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable("id") Long id) {
        Libro libroExistente = libroRepository.findById(id).orElse(null);
        if (libroExistente != null) {
            libroRepository.delete(libroExistente);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/libros/{categoriaId}")
    public ResponseEntity<List<Libro>> obtenerLibrosPorCategoria(@PathVariable("categoriaId") Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId).orElse(null);
        if (categoria != null) {
            List<Libro> libros = libroRepository.findByCategoria(categoria);
            return new ResponseEntity<>(libros, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
