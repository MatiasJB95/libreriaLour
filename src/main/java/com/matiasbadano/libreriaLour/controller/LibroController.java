package com.matiasbadano.libreriaLour.controller;
import com.matiasbadano.libreriaLour.domain.carrito.CarritoItemRepository;
import com.matiasbadano.libreriaLour.domain.carrito.CarritoRepository;
import com.matiasbadano.libreriaLour.domain.categoria.Categoria;
import com.matiasbadano.libreriaLour.domain.categoria.CategoriaDTO;
import com.matiasbadano.libreriaLour.domain.categoria.CategoriaRepository;
import com.matiasbadano.libreriaLour.domain.libros.Libro;
import com.matiasbadano.libreriaLour.domain.libros.LibroDTO;
import com.matiasbadano.libreriaLour.domain.libros.LibroDestacadoDTO;
import com.matiasbadano.libreriaLour.domain.libros.LibroRepository;
import jakarta.transaction.Transactional;
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
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CarritoItemRepository carritoItemRepository;


    @GetMapping
    public ResponseEntity<List<LibroDTO>> obtenerTodosLosLibros() {
        List<Libro> libros = libroRepository.findAll();
        List<LibroDTO> librosDTO = libros.stream()
                .map(LibroDTO::fromLibro)
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
    public ResponseEntity<String> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroRepository.save(libro);
        libro.setCategoria(categoria);

        return ResponseEntity.status(HttpStatus.CREATED).body("Libro creado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable("id") Long id, @RequestBody Libro libroActualizado) {
        Optional<Libro> optionalLibro = libroRepository.findById(id);
        if (optionalLibro.isPresent()) {
            Libro libro = optionalLibro.get();
            libro.setTitulo(libroActualizado.getTitulo());
            libro.setAutor(libroActualizado.getAutor());
            libro.setPrecio(libroActualizado.getPrecio());
            libro.setEditorial(libroActualizado.getEditorial());

            Long categoriaId = (long) libroActualizado.getCategoria().getId();
            Categoria categoria = categoriaRepository.findById(categoriaId)
                    .orElseThrow(() -> new IllegalArgumentException("ID de categoría inválido"));
            libro.setCategoria(categoria);

            Libro libroActualizadoEntity = libroRepository.save(libro);
            return new ResponseEntity<>(libroActualizadoEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        carritoItemRepository.deleteByLibroId(id);
        libroRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/destacados")
    public ResponseEntity<List<LibroDestacadoDTO>> obtenerLibrosDestacados() {
        List<Libro> librosDestacados = libroRepository.findByDestacadoTrue();
        List<LibroDestacadoDTO> librosDestacadosDTO = new ArrayList<>();

        for (Libro libro : librosDestacados) {
            LibroDestacadoDTO libroDestacadoDTO = new LibroDestacadoDTO();
            libroDestacadoDTO.setTitulo(libro.getTitulo());
            libroDestacadoDTO.setAutor(libro.getAutor());
            libroDestacadoDTO.setEditorial(libro.getEditorial());
            libroDestacadoDTO.setPrecio(libro.getPrecio());

            CategoriaDTO categoriaDTO = new CategoriaDTO();
            categoriaDTO.setNombre(libro.getCategoria().getNombre());
            libroDestacadoDTO.setCategoria(categoriaDTO);

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
                libro.getCategoriaDTO()
        );
    }

    private Libro convertirADominio(LibroDTO libroDTO) {
        Libro libro = new Libro();
        libro.setTitulo(libroDTO.getTitulo());
        libro.setAutor(libroDTO.getAutor());
        libro.setEditorial(libroDTO.getEditorial());
        libro.setPrecio(libroDTO.getPrecio());

        Categoria categoria = categoriaRepository.findById(libroDTO.getCategoria().getId())
                .orElseThrow(() -> new IllegalArgumentException("ID de categoría inválido"));
        libro.setCategoria(categoria);

        return libro;
    }
}
