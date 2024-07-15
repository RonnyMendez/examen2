package org.examen.alquiler.services;

import org.examen.alquiler.models.Automovil;
import org.examen.alquiler.repositories.AutomovilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutomovilService {

    @Autowired
    private AutomovilRepository automovilRepository;

    public List<Automovil> getAutomoviles() {
        return automovilRepository.findAll();
    }

    public Optional<Automovil> getAutomovilPorId(Long id) {
        return automovilRepository.findById(id);
    }

    public void guardarAutomovil(Automovil automovil) {
        // Aquí irá lógica de validación u otras operaciones antes de guardar
        automovilRepository.save(automovil);
    }

    public void deleteAutomovil(Long id) {
        automovilRepository.deleteById(id);
    }
}