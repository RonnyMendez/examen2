package org.examen.alquiler.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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

    @Size(min = 8)
    @Column(name = "password")
    private String password;

    @Email
    @Column(name = "email", unique = true)
    private String email;
    private String rol;
    private String nombre;

    public Usuario() {

    }


}
