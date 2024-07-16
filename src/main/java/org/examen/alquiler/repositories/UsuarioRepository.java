package org.examen.alquiler.repositories;

import org.examen.alquiler.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // No se necesitan métodos adicionales aquí por ahora, JpaRepository proporciona métodos CRUD estándar
}