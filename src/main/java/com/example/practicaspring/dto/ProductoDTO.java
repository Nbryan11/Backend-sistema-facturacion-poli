package com.example.practicaspring.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProductoDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Float precio;
    private Integer stock;
    private LocalDateTime fechaCreacion;
    private String codigoBarras;
    private String categoria;
    private Boolean activo;
}