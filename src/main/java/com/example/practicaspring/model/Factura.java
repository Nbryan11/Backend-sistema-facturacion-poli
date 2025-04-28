package com.example.practicaspring.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    private Float total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "impuesto_id")
    private Impuesto impuesto;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemFactura> items = new ArrayList<>();


    // Método auxiliar para calcular total
    public void calcularTotal() {
        if (this.items != null) {
            this.total = this.items.stream()
                    .map(ItemFactura::getSubtotal)
                    .reduce(0f, Float::sum);
        }
    }
}