package com.example.practicaspring.dto;

import lombok.Data;

import java.util.List;

@Data
public class FacturaRequest {
    private Integer clienteId;
    private Integer impuestoId;

    private List<ItemFacturaRequestDTO> items;

}