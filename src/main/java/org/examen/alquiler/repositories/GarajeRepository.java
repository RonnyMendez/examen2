package org.examen.alquiler.repositories;

import org.examen.alquiler.models.Garaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarajeRepository extends JpaRepository<Garaje, Long> {
}
