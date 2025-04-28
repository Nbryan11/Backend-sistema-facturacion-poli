package com.example.practicaspring.dto;

import lombok.Data;

@Data
public class DireccionDTO {
    private String calle;
    private String ciudad;
    private String provincia;
    private String codigoPostal;
    private String pais;
}