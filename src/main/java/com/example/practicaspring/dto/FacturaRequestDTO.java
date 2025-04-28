package com.example.practicaspring.dto;

import lombok.Data;

import java.util.List;

public class FacturaRequestDTO {

        private Integer clienteId;
        private Integer impuestoId;

    public Integer getClienteId() {
        return clienteId;
    }

    public Integer getImpuestoId() {
        return impuestoId;
    }

    public List<ItemFacturaRequestDTO> getItems() {
        return items;
    }

    private List<ItemFacturaRequestDTO> items;

    }

