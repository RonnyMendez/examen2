package org.examen.alquiler.repositories;

import org.examen.alquiler.models.Automovil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomovilRepository extends JpaRepository<Automovil, Long> {
}