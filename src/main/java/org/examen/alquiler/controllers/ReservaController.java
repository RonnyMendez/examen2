package org.examen.alquiler.controllers;

import org.examen.alquiler.DTO.ReservaDTO;
import org.examen.alquiler.models.Automovil;
import org.examen.alquiler.models.Cliente;
import org.examen.alquiler.models.Reserva;
import org.examen.alquiler.services.AutomovilService;
import org.examen.alquiler.services.ClienteService;
import org.examen.alquiler.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final AutomovilService automovilService;
    private final ClienteService clienteService;

    @Autowired
    public ReservaController(ReservaService reservaService, AutomovilService automovilService, ClienteService clienteService) {
        this.reservaService = reservaService;
        this.automovilService = automovilService;
        this.clienteService = clienteService;
    }


    @GetMapping
    public List<Reserva> obtenerReservas() {
        return reservaService.getReservas();
    }

    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        // Convertir los IDs de automóviles en objetos Automovil
        List<Automovil> automoviles = reservaDTO.getAutomoviles().stream()
                .map(automovilId -> {
                    Optional<Automovil> automovilOptional = automovilService.getAutomovilPorId(automovilId);
                    return automovilOptional.orElse(null); // Puedes manejar el Optional aquí
                })
                .filter(Objects::nonNull) // Filtrar los automóviles no nulos
                .collect(Collectors.toList());

        // Obtener el cliente
        Optional<Cliente> clienteOptional = clienteService.getClientePorId(reservaDTO.getClienteId());
        Cliente cliente = clienteOptional.orElse(null); // Puedes manejar el Optional aquí

        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }

        // Crear una instancia de Reserva y configurar los datos
        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setFechaInicio(reservaDTO.getFechaInicio());
        reserva.setFechaFinal(reservaDTO.getFechaFinal());
        reserva.setPrecioTotal(reservaDTO.getPrecioTotal());
        reserva.setAutomoviles(automoviles);

        // Guardar la reserva en la base de datos usando el servicio correspondiente
        reservaService.guardarReserva(reserva);

        return ResponseEntity.ok().build();
    }
}
