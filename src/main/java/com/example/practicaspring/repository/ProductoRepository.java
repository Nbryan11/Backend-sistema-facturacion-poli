package com.example.practicaspring.repository;

import com.example.practicaspring.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByCategoria(String categoria);
    List<Producto> findByActivoTrue();
}