package com.example.practicaspring.service;

import com.example.practicaspring.model.Impuesto;
import com.example.practicaspring.repository.ImpuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ImpuestoService {
    @Autowired
    private ImpuestoRepository impuestoRepository;

    public List<Impuesto> getAllImpuestos() {
        return impuestoRepository.findAll();
    }

    public Optional<Impuesto> getImpuestoById(Integer id) {
        return impuestoRepository.findById(id);
    }

    public Impuesto getImpuestoEntityById(Integer id) {
        return impuestoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impuesto no encontrado"));
    }

    public Impuesto saveImpuesto(Impuesto impuesto) {
        return impuestoRepository.save(impuesto);
    }

    public Impuesto updateImpuesto(Integer id, Impuesto impuestoDetails) {
        Impuesto impuesto = impuestoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impuesto no encontrado"));

        impuesto.setNombre(impuestoDetails.getNombre());
        impuesto.setPorcentaje(impuestoDetails.getPorcentaje());

        return impuestoRepository.save(impuesto);
    }

    public void deleteImpuesto(Integer id) {
        impuestoRepository.deleteById(id);
    }


}