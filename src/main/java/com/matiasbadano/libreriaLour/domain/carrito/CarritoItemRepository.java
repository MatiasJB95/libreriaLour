package com.matiasbadano.libreriaLour.domain.carrito;
import com.matiasbadano.libreriaLour.domain.libros.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {
    List<CarritoItem> findByCarrito(Carrito carrito);
    Optional<CarritoItem> findByCarritoAndLibro(Carrito carrito, Libro libro);
    void deleteByCarrito(Carrito carrito);
    void deleteByCarritoAndLibro(Carrito carrito, Libro libro);
}
