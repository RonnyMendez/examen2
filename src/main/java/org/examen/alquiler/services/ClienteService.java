package org.examen.alquiler.services;

import org.examen.alquiler.models.Cliente;
import org.examen.alquiler.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getClientes(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClientePorId(Long id){
        return clienteRepository.findById(id);
    }

    public void guardarCliente(Cliente cliente){
        // Aquí irá lógica de validación u otras operaciones antes de guardar
        clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id){
        clienteRepository.deleteById(id);
    }
}
