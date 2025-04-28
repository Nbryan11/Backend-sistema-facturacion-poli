package com.example.practicaspring.repository;

import com.example.practicaspring.model.ItemFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemFacturaRepository extends JpaRepository<ItemFactura, Integer> {
    List<ItemFactura> findByFacturaId(Integer facturaId);
}