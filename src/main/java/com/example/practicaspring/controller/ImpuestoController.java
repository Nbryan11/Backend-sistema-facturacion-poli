package com.example.practicaspring.controller;

import com.example.practicaspring.model.Impuesto;
import com.example.practicaspring.service.ImpuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/impuestos")
public class ImpuestoController {

    @Autowired
    private ImpuestoService impuestoService;

    @GetMapping
    public List<Impuesto> getAllImpuestos() {
        return impuestoService.getAllImpuestos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Impuesto> getImpuestoById(@PathVariable Integer id) {
        return impuestoService.getImpuestoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Impuesto createImpuesto(@RequestBody Impuesto impuesto) {
        return impuestoService.saveImpuesto(impuesto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Impuesto> updateImpuesto(@PathVariable Integer id, @RequestBody Impuesto impuestoDetails) {
        return ResponseEntity.ok(impuestoService.updateImpuesto(id, impuestoDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImpuesto(@PathVariable Integer id) {
        impuestoService.deleteImpuesto(id);
        return ResponseEntity.noContent().build();
    }

}