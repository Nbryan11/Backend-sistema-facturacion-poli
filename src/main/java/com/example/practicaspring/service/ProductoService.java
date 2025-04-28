package com.example.practicaspring.service;

import com.example.practicaspring.model.Producto;
import com.example.practicaspring.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProductoById(Integer id) {
        return productoRepository.findById(id);
    }

    public Producto getProductoEntityById(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Integer id, Producto productoDetails) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setNombre(productoDetails.getNombre());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setStock(productoDetails.getStock());
        producto.setCodigoBarras(productoDetails.getCodigoBarras());
        producto.setCategoria(productoDetails.getCategoria());
        producto.setActivo(productoDetails.getActivo());

        return productoRepository.save(producto);
    }

    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public Producto actualizarStock(Integer id, Integer cantidad) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setStock(producto.getStock() + cantidad);
        return productoRepository.save(producto);
    }
}