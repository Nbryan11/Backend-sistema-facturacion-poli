package com.example.practicaspring.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "impuestos")
public class Impuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private Float porcentaje;
}