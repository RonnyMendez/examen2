package org.examen.alquiler.controllers;

import org.examen.alquiler.models.Cliente;
import org.examen.alquiler.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/clientes")
public class ClienteController {

    @Autowired
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> obtenerClientes() {
        return clienteService.getClientes();
    }

    @GetMapping("/{clienteId}")
    public Optional<Cliente> obtenerClientePorId(@PathVariable("clienteId") Long clienteId) {
        return clienteService.getClientePorId(clienteId);
    }

    @PostMapping
    public void guardarCliente(@RequestBody Cliente cliente) {
        clienteService.guardarCliente(cliente);
    }

    @DeleteMapping("/delete/{clienteId}")
    public void deleteCliente(@PathVariable("clienteId") Long clienteId) {
        clienteService.deleteCliente(clienteId);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Void> actualizarCliente(@PathVariable("clienteId") Long clienteId, @RequestBody Cliente cliente) {
        Optional<Cliente> existingCliente = clienteService.getClientePorId(clienteId);
        if (existingCliente.isPresent()) {
            // Actualizar los campos que se pueden modificar
            existingCliente.get().setDNI(cliente.getDNI());
            existingCliente.get().setNombre(cliente.getNombre());
            existingCliente.get().setDireccion(cliente.getDireccion());
            existingCliente.get().setTelefono(cliente.getTelefono());

            clienteService.guardarCliente(existingCliente.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
