package com.matiasbadano.libreriaLour.domain.carrito;
import com.matiasbadano.libreriaLour.domain.libros.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {
    Optional<Carrito> findById(long id);
    List<CarritoItem> findByCarrito(Carrito carrito);
    Optional<CarritoItem> findByCarritoAndLibro(Carrito carrito, Libro libro);
    void deleteByCarrito(Carrito carrito);
    void deleteByCarritoAndLibro(Carrito carrito, Libro libro);
    @Modifying
    @Query("DELETE FROM CarritoItem ci WHERE ci.libro.id = :libroId")
    void deleteByLibroId(@Param("libroId") Long libroId);
}
