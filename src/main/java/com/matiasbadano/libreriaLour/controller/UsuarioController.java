package com.matiasbadano.libreriaLour.controller;

import com.matiasbadano.libreriaLour.domain.carrito.Carrito;
import com.matiasbadano.libreriaLour.domain.carrito.CarritoService;
import com.matiasbadano.libreriaLour.domain.libros.Libro;
import com.matiasbadano.libreriaLour.domain.usuarios.Usuario;
import com.matiasbadano.libreriaLour.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuarioActualizado) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setPassword(usuarioActualizado.getPassword());
            usuario.setRoles(usuarioActualizado.getRoles());
<<<<<<< HEAD
=======

>>>>>>> 94caf48a7cdd5709faffbb8f9781228931e98f18

            Usuario usuarioActualizadoU = usuarioRepository.save(usuario);
            return new ResponseEntity<>(usuarioActualizadoU, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable("id") Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            usuarioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{id}/carrito")
    public ResponseEntity<Carrito> agregarLibroAlCarrito(@PathVariable("id") Long id, @RequestBody Libro libro) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Carrito carrito = usuario.getCarrito();

            Carrito carritoActualizado = carritoService.agregarLibroAlCarrito(carrito, libro);
            return new ResponseEntity<>(carritoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/carrito/{libroId}")
    public ResponseEntity<Carrito> eliminarLibroDelCarrito(@PathVariable("id") Long id, @PathVariable("libroId") Long libroId) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Carrito carrito = usuario.getCarrito();

            Carrito carritoActualizado = carritoService.eliminarLibroDelCarrito(carrito, libroId);
            return new ResponseEntity<>(carritoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

