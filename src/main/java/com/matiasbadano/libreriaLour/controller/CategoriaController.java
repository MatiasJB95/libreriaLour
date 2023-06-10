package com.matiasbadano.libreriaLour.controller;

import com.matiasbadano.libreriaLour.domain.categoria.Categoria;
import com.matiasbadano.libreriaLour.domain.categoria.CategoriaDTO;
import com.matiasbadano.libreriaLour.domain.categoria.CategoriaRepository;
import com.matiasbadano.libreriaLour.domain.libros.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> obtenerTodasLasCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaDTO> categoriasDTO = categorias.stream()
                .map(categoria -> new CategoriaDTO(categoria.getId(), categoria.getNombre()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(categoriasDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable("id") Long id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaRepository.save(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable("id") Long id, @RequestBody Categoria categoriaActualizada) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            categoria.setNombre(categoriaActualizada.getNombre());

            Categoria categoriaActualizadaEntity = categoriaRepository.save(categoria);
            return new ResponseEntity<>(categoriaActualizadaEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable("id") Long id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            categoriaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}/libros")
    public ResponseEntity<List<Libro>> obtenerLibrosPorCategoria(@PathVariable("id") Long id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            List<Libro> libros = categoria.getLibros();
            return new ResponseEntity<>(libros, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}