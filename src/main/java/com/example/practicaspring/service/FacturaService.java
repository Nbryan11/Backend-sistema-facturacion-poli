package com.example.practicaspring.service;
import com.example.practicaspring.dto.*;
import com.example.practicaspring.model.*;
import com.example.practicaspring.repository.*;

import com.example.practicaspring.model.Factura;
import com.example.practicaspring.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ImpuestoService impuestoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ItemFacturaRepository itemFacturaRepository;

    public FacturaResponseDTO createFactura(FacturaRequestDTO request) {
        Factura factura = new Factura();
        factura.setFechaCreacion(LocalDate.now());

        // Establecer cliente
        Cliente cliente = clienteService.getClienteEntityById(request.getClienteId());
        factura.setCliente(cliente);

        // Establecer impuesto si existe
        if (request.getImpuestoId() != null) {
            Impuesto impuesto = impuestoService.getImpuestoEntityById(request.getImpuestoId());
            factura.setImpuesto(impuesto);
        }

        // Guardar la factura primero para obtener el ID
        Factura savedFactura = facturaRepository.save(factura);

        // Procesar items
        List<ItemFactura> items = new ArrayList<>();
        float totalFactura = 0f;

        for (ItemFacturaRequestDTO itemRequest : request.getItems()) {
            Producto producto = productoService.getProductoEntityById(itemRequest.getProductoId());

            ItemFactura item = new ItemFactura();
            item.setFactura(savedFactura);
            item.setProducto(producto);
            item.setCantidad(itemRequest.getCantidad());
            item.setPrecioUnitario(producto.getPrecio());
            item.setSubtotal(producto.getPrecio() * itemRequest.getCantidad());

            items.add(item);
            totalFactura += item.getSubtotal();

            // Actualizar stock del producto
            productoService.actualizarStock(producto.getId(), -itemRequest.getCantidad());
        }

        // Aplicar impuesto si existe
        if (factura.getImpuesto() != null) {
            totalFactura += totalFactura * (factura.getImpuesto().getPorcentaje() / 100);
        }

        // Guardar items
        itemFacturaRepository.saveAll(items);

        // Actualizar total y guardar factura
        savedFactura.setTotal(totalFactura);
        savedFactura.setItems(items);
        Factura finalFactura = facturaRepository.save(savedFactura);

        return convertToDTO(finalFactura);
    }

    @Transactional(readOnly = true)
    public List<FacturaResponseDTO> findAllFacturas() {
        return facturaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<FacturaResponseDTO> findFacturaById(Integer id) {
        return facturaRepository.findById(id)
                .map(this::convertToDTO);
    }

    private FacturaResponseDTO convertToDTO(Factura factura) {
        FacturaResponseDTO dto = new FacturaResponseDTO();
        dto.setId(factura.getId());
        dto.setFechaCreacion(factura.getFechaCreacion());
        dto.setTotal(factura.getTotal());

        // Convertir cliente a DTO
        Cliente cliente = factura.getCliente();
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellido(cliente.getApellido());
        clienteDTO.setTelefono(cliente.getTelefono());
        clienteDTO.setDocumento(cliente.getDocumento());
        clienteDTO.setFechaCreacion(cliente.getFechaCreacion());

        // Convertir dirección a DTO
        DireccionDTO direccionDTO = new DireccionDTO();
        direccionDTO.setCalle(cliente.getDireccion().getCalle());
        direccionDTO.setCiudad(cliente.getDireccion().getCiudad());
        direccionDTO.setProvincia(cliente.getDireccion().getProvincia());
        direccionDTO.setCodigoPostal(cliente.getDireccion().getCodigoPostal());
        direccionDTO.setPais(cliente.getDireccion().getPais());

        clienteDTO.setDireccion(direccionDTO);
        dto.setCliente(clienteDTO);

        // Convertir impuesto a DTO si existe
        if (factura.getImpuesto() != null) {
            ImpuestoDTO impuestoDTO = new ImpuestoDTO();
            impuestoDTO.setId(factura.getImpuesto().getId());
            impuestoDTO.setNombre(factura.getImpuesto().getNombre());
            impuestoDTO.setPorcentaje(factura.getImpuesto().getPorcentaje());
            dto.setImpuesto(impuestoDTO);
        }

        // Convertir items a DTO
        List<ItemFacturaResponseDTO> itemsDTO = factura.getItems().stream()
                .map(this::convertItemToDTO)
                .collect(Collectors.toList());
        dto.setItems(itemsDTO);

        return dto;
    }

    private ItemFacturaResponseDTO convertItemToDTO(ItemFactura item) {
        ItemFacturaResponseDTO itemDTO = new ItemFacturaResponseDTO();
        itemDTO.setId(item.getId());
        itemDTO.setCantidad(item.getCantidad());
        itemDTO.setPrecioUnitario(item.getPrecioUnitario());
        itemDTO.setSubtotal(item.getSubtotal());

        // Convertir producto a DTO
        Producto producto = item.getProducto();
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setDescripcion(producto.getDescripcion());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setStock(producto.getStock());
        productoDTO.setFechaCreacion(producto.getFechaCreacion());
        productoDTO.setCodigoBarras(producto.getCodigoBarras());
        productoDTO.setCategoria(producto.getCategoria());
        productoDTO.setActivo(producto.getActivo());

        itemDTO.setProducto(productoDTO);
        return itemDTO;
    }
}