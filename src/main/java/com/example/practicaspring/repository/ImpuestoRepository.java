package com.example.practicaspring.repository;

import com.example.practicaspring.model.Impuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImpuestoRepository extends JpaRepository<Impuesto, Integer> {
}