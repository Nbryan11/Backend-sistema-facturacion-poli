package com.example.practicaspring.dto;
import lombok.Data;

@Data
public class ItemFacturaResponseDTO {
    private Integer id;
    private ProductoDTO producto;
    private Integer cantidad;
    private Float precioUnitario;
    private Float subtotal;
}
