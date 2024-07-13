package org.examen.alquiler.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;
    @Column(name = "usuario", unique = true)
    private String usuario;
    private String password;
    @Column(name = "email", unique = true)
    private String email;
    private String rol;
    private String nombre;

    public Usuario() {

    }


}
