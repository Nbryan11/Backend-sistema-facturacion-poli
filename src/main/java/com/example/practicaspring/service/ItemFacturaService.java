package com.example.practicaspring.service;

import com.example.practicaspring.model.ItemFactura;
import com.example.practicaspring.repository.ItemFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ItemFacturaService {
    @Autowired
    private ItemFacturaRepository itemFacturaRepository;

    @Autowired
    private ProductoService productoService;

    public List<ItemFactura> getAllItems() {
        return itemFacturaRepository.findAll();
    }

    public Optional<ItemFactura> getItemById(Integer id) {
        return itemFacturaRepository.findById(id);
    }

    public ItemFactura saveItem(ItemFactura item) {
        return itemFacturaRepository.save(item);
    }

    public ItemFactura updateItem(Integer id, ItemFactura itemDetails) {
        ItemFactura item = itemFacturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        // Calculamos la diferencia de cantidad para actualizar el stock
        int diferenciaCantidad = itemDetails.getCantidad() - item.getCantidad();

        item.setFactura(itemDetails.getFactura());
        item.setProducto(itemDetails.getProducto()); // Ahora usamos el objeto Producto completo
        item.setCantidad(itemDetails.getCantidad());
        item.setPrecioUnitario(itemDetails.getPrecioUnitario());

        // El subtotal se calcula automáticamente con @PreUpdate (ver la entidad)
        // Pero podemos forzar el cálculo si es necesario
        item.calcularSubtotal();

        // Actualizamos el stock del producto
        if (diferenciaCantidad != 0 && item.getProducto() != null) {
            productoService.actualizarStock(
                    item.getProducto().getId(),
                    -diferenciaCantidad // Restamos la diferencia
            );
        }

        return itemFacturaRepository.save(item);
    }

    public void deleteItem(Integer id) {
        itemFacturaRepository.deleteById(id);
    }

    public List<ItemFactura> getItemsByFacturaId(Integer facturaId) {
        return itemFacturaRepository.findByFacturaId(facturaId);
    }
}