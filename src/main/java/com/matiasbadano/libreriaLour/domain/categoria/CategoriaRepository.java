package com.matiasbadano.libreriaLour.domain.categoria;


import com.matiasbadano.libreriaLour.domain.libros.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository  extends JpaRepository<Categoria, Long> {

}
