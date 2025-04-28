package com.example.practicaspring.service;

import com.example.practicaspring.model.Cliente;
import com.example.practicaspring.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {

        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Integer id) {
        return clienteRepository.findById(id);
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Integer id, Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setNombre(clienteDetails.getNombre());
        cliente.setApellido(clienteDetails.getApellido());
        cliente.setTelefono(clienteDetails.getTelefono());
        cliente.setDireccion(clienteDetails.getDireccion());
        cliente.setDocumento(clienteDetails.getDocumento());

        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

    public Cliente getClienteEntityById(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
}