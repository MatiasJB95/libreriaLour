package com.matiasbadano.libreriaLour.domain.libros;

import com.matiasbadano.libreriaLour.domain.categoria.Categoria;
import com.matiasbadano.libreriaLour.domain.libros.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByCategoria(Categoria categoria);

    List<Libro> findByDestacado(boolean destacado);
    List<Libro> findByDestacadoTrue();
    void deleteById(Long id);
}
