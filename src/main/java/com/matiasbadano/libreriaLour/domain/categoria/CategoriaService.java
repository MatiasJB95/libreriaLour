package com.matiasbadano.libreriaLour.domain.categoria;

import com.matiasbadano.libreriaLour.infra.errores.CategoriaNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException("No se encontró la categoría con ID: " + id));
    }
}