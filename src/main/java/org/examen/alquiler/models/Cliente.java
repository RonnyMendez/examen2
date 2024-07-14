package org.examen.alquiler.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteCodigo;
    @Column(name = "DNI", unique = true)
    private String DNI;
    @Column(name = "nombre")
    private String nombre;
    private String direccion;
    private String telefono;

}
