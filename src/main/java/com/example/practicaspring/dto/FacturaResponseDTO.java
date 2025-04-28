package com.example.practicaspring.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class FacturaResponseDTO {
    private Integer id;
    private LocalDate fechaCreacion;
    private Float total;
    private ClienteDTO cliente;
    private ImpuestoDTO impuesto;
    private List<ItemFacturaResponseDTO> items;
}