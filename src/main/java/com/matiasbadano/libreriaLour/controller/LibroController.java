package com.matiasbadano.libreriaLour.controller;
import com.matiasbadano.libreriaLour.domain.categoria.Categoria;
import com.matiasbadano.libreriaLour.domain.categoria.CategoriaDTO;
import com.matiasbadano.libreriaLour.domain.categoria.CategoriaRepository;
import com.matiasbadano.libreriaLour.domain.libros.Libro;
import com.matiasbadano.libreriaLour.domain.libros.LibroDTO;
import com.matiasbadano.libreriaLour.domain.libros.LibroDestacadoDTO;
import com.matiasbadano.libreriaLour.domain.libros.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private LibroRepository libroRepository;
    private Categoria categoria;
    private CategoriaRepository categoriaRepository;


    @GetMapping
    public ResponseEntity<List<LibroDTO>> obtenerTodosLosLibros() {
        List<Libro> libros = libroRepository.findAll();
        List<LibroDTO> librosDTO = libros.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(librosDTO, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        Optional<Libro> optionalLibro = libroRepository.findById(id);
        if (optionalLibro.isPresent()) {
            Libro libro = optionalLibro.get();
            return new ResponseEntity<>(libro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroRepository.save(libro);
        libro.setCategoria(categoria);
        LibroDTO nuevoLibroDTO = convertirADTO(nuevoLibro);
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable("id") Long id, @RequestBody Libro libroActualizado) {
        Optional<Libro> optionalLibro = libroRepository.findById(id);
        if (optionalLibro.isPresent()) {
            Libro libro = optionalLibro.get();
            libro.setTitulo(libroActualizado.getTitulo());
            libro.setAutor(libroActualizado.getAutor());
            libro.setPrecio(libroActualizado.getPrecio());
            libro.setCategoria(libroActualizado.getCategoria());
            Libro libroActualizadoEntity = libroRepository.save(libro);
            return new ResponseEntity<>(libroActualizadoEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/destacados")
    public ResponseEntity<List<LibroDestacadoDTO>> obtenerLibrosDestacados() {
        List<Libro> librosDestacados = libroRepository.findByDestacadoTrue();
        List<LibroDestacadoDTO> librosDestacadosDTO = new ArrayList<>();

        for (Libro libro : librosDestacados) {
            LibroDestacadoDTO libroDestacadoDTO = new LibroDestacadoDTO();
            libroDestacadoDTO.setId(libro.getId());
            libroDestacadoDTO.setTitulo(libro.getTitulo());
            libroDestacadoDTO.setAutor(libro.getAutor());
            libroDestacadoDTO.setEditorial(libro.getEditorial());
            libroDestacadoDTO.setPrecio(libro.getPrecio());
            libroDestacadoDTO.setDestacado(libro.isDestacado());
            libroDestacadoDTO.setCategoria(CategoriaDTO.fromCategoria(libro.getCategoria()));

            librosDestacadosDTO.add(libroDestacadoDTO);
        }

        return new ResponseEntity<>(librosDestacadosDTO, HttpStatus.OK);
    }
    private LibroDTO convertirADTO(Libro libro) {
        Categoria categoria = libro.getCategoria();
        return new LibroDTO(
                libro.getId(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getEditorial(),
                libro.getPrecio(),
                libro.isDestacado(),
               libro.getCategoriaDTO()
        );
    }

    private Libro convertirADominio(LibroDTO libroDTO) {
        Libro libro = new Libro();
        libro.setTitulo(libroDTO.getTitulo());
        libro.setAutor(libroDTO.getAutor());
        libro.setEditorial(libroDTO.getEditorial());
        libro.setPrecio(libroDTO.getPrecio());
        libro.setDestacado(libroDTO.isDestacado());

        Categoria categoria = categoriaRepository.findById(libroDTO.getCategoria().getId())
                .orElseThrow(() -> new IllegalArgumentException("ID de categoría inválido"));
        libro.setCategoria(categoria);

        return libro;
    }


}