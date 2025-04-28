package com.example.practicaspring.repository;

import com.example.practicaspring.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    List<Factura> findByClienteId(Integer clienteId);
}