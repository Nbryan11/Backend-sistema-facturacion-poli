package com.example.practicaspring.dto;

import lombok.Data;

@Data
public class ItemFacturaRequestDTO {
    private Integer productoId;
    private Integer cantidad;
}