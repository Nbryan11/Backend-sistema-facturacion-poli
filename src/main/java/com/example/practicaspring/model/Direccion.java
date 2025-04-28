package com.example.practicaspring.model;

import lombok.Data;
import jakarta.persistence.Embeddable;

@Embeddable
@Data
public class Direccion {
    private String calle;
    private String ciudad;
    private String provincia;
    private String codigoPostal;
    private String pais;
}