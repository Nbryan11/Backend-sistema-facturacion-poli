package com.example.practicaspring.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ClienteDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    private DireccionDTO direccion;
    private String documento;
    private LocalDateTime fechaCreacion;
}