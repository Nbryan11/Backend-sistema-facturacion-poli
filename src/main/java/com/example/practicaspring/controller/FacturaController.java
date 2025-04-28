package com.example.practicaspring.controller;
import com.example.practicaspring.dto.*;

import com.example.practicaspring.dto.FacturaRequest;
import com.example.practicaspring.model.Factura;
import com.example.practicaspring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping
    public ResponseEntity<FacturaResponseDTO> createFactura(@RequestBody FacturaRequestDTO request) {
        FacturaResponseDTO savedFactura = facturaService.createFactura(request);
        return ResponseEntity.ok(savedFactura);
    }

    @GetMapping
    public List<FacturaResponseDTO> getAllFacturas() {
        return facturaService.findAllFacturas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaResponseDTO> getFacturaById(@PathVariable Integer id) {
        return facturaService.findFacturaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}