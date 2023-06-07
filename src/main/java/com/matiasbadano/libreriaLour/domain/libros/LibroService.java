package com.matiasbadano.libreriaLour.domain.libros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;



    public List<Libro> obtenerLibrosDestacados() {
        return libroRepository.findByDestacado(true);
    }


    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }

    public void actualizarLibro(Libro libro) {
        libroRepository.save(libro);
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }
}