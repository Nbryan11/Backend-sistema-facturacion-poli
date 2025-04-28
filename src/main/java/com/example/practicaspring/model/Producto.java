    package com.example.practicaspring.model;

    import jakarta.persistence.*;
    import lombok.Data;

    import java.time.LocalDateTime;

    @Entity
    @Data
    @Table(name = "productos")
    public class Producto {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String nombre;
        private String descripcion;
        private Float precio;
        private Integer stock;

        @Column(name = "fecha_creacion")
        private LocalDateTime fechaCreacion;

        // Puedes agregar más campos según necesites:
        private String codigoBarras;
        private String categoria;
        private Boolean activo = true;
    }