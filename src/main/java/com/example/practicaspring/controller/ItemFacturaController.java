package com.example.practicaspring.controller;

import com.example.practicaspring.model.ItemFactura;
import com.example.practicaspring.service.ItemFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/items-factura")
public class ItemFacturaController {

    @Autowired
    private ItemFacturaService itemFacturaService;

    @GetMapping
    public List<ItemFactura> getAllItems() {
        return itemFacturaService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemFactura> getItemById(@PathVariable Integer id) {
        return itemFacturaService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ItemFactura createItem(@RequestBody ItemFactura item) {
        return itemFacturaService.saveItem(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemFactura> updateItem(@PathVariable Integer id, @RequestBody ItemFactura itemDetails) {
        return ResponseEntity.ok(itemFacturaService.updateItem(id, itemDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer id) {
        itemFacturaService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/factura/{facturaId}")
    public List<ItemFactura> getItemsByFactura(@PathVariable Integer facturaId) {
        return itemFacturaService.getItemsByFacturaId(facturaId);
    }
}