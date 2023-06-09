package com.matiasbadano.libreriaLour.domain.carrito;
import com.matiasbadano.libreriaLour.domain.libros.Libro;
import com.matiasbadano.libreriaLour.domain.libros.LibroRepository;
import com.matiasbadano.libreriaLour.infra.errores.CarritoNotFoundException;
import com.matiasbadano.libreriaLour.infra.errores.LibroNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarritoService {
    private final CarritoRepository carritoRepository;
    private final CarritoItemRepository carritoItemRepository;
    private final LibroRepository libroRepository;

    @Autowired
    public CarritoService(CarritoRepository carritoRepository, CarritoItemRepository carritoItemRepository, LibroRepository libroRepository) {
        this.carritoRepository = carritoRepository;
        this.carritoItemRepository = carritoItemRepository;
        this.libroRepository = libroRepository;
    }

    public Carrito obtenerCarritoPorId(Long id) {
        return carritoRepository.findById(id)
                .orElseThrow(() -> new CarritoNotFoundException("No se encontró el carrito con ID: " + id));
    }

    public List<Libro> obtenerProductosEnCarrito(Carrito carrito) {
        List<CarritoItem> carritoItems = carritoItemRepository.findByCarrito(carrito);
        return carritoItems.stream()
                .map(CarritoItem::getLibro)
                .collect(Collectors.toList());
    }

    public void agregarAlCarrito(Carrito carrito, Long libroId, int cantidad) {
        Optional<Libro> libroOptional = libroRepository.findById(libroId);
        Libro libro = libroOptional.orElseThrow(() -> new LibroNotFoundException("No se encontró el libro con ID: " + libroId));

        Optional<CarritoItem> carritoItemOptional = carritoItemRepository.findByCarritoAndLibro(carrito, libro);
        if (carritoItemOptional.isPresent()) {
            CarritoItem carritoItem = carritoItemOptional.get();
            carritoItem.setCantidad(carritoItem.getCantidad() + cantidad);
            carritoItemRepository.save(carritoItem);
        } else {
            CarritoItem carritoItem = new CarritoItem(carrito, libro, cantidad);
            carritoItemRepository.save(carritoItem);
        }
    }

    public void eliminarDelCarrito(Carrito carrito, Long libroId) {
        Optional<Libro> libroOptional = libroRepository.findById(libroId);
        Libro libro = libroOptional.orElseThrow(() -> new LibroNotFoundException("No se encontró el libro con ID: " + libroId));

        Optional<CarritoItem> carritoItemOptional = carritoItemRepository.findByCarritoAndLibro(carrito, libro);
        if (carritoItemOptional.isPresent()) {
            CarritoItem carritoItem = carritoItemOptional.get();
            carritoItemRepository.delete(carritoItem);
        }
    }

    public void vaciarCarrito(Carrito carrito) {
        carritoItemRepository.deleteByCarrito(carrito);
    }
}