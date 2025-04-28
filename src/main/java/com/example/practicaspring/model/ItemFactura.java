package com.example.practicaspring.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "items_factura")
public class ItemFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factura_id",  nullable = false)
    private Factura factura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id",  nullable = false)
    private Producto producto;

    private Integer cantidad;
    private Float precioUnitario;
    private Float subtotal;

    @PrePersist
    @PreUpdate
    public void calcularSubtotal() {
        if (producto != null && cantidad != null) {
            this.precioUnitario = producto.getPrecio();
            this.subtotal = producto.getPrecio() * cantidad;
        }
    }
}