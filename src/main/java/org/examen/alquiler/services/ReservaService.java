package org.examen.alquiler.services;

import org.examen.alquiler.models.Garaje;
import org.examen.alquiler.models.Reserva;
import org.examen.alquiler.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> getReservas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> getReservaPorId(Long id){
        return reservaRepository.findById(id);
    }

    public void guardarReserva(Reserva reserva){
        reservaRepository.save(reserva);
    }

    public void deleteReserva(Long id){
        reservaRepository.deleteById(id);
    }
}
