package org.examen.alquiler.services;


import org.examen.alquiler.models.Garaje;
import org.examen.alquiler.repositories.GarajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GarajeService {

    @Autowired
    private GarajeRepository garajeRepository;

    public List<Garaje> getGarajes(){
        return garajeRepository.findAll();
    }

    public Optional<Garaje> getGarajePorId(Long id){
        return garajeRepository.findById(id);
    }

    public void guardarGaraje(Garaje garaje){
        garajeRepository.save(garaje);
    }

    public void deleteGaraje(Long id){
        garajeRepository.deleteById(id);
    }

}
