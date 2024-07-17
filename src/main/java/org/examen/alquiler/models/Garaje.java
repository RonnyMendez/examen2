package org.examen.alquiler.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "garajes")
public class Garaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long garajeId;

    @NotEmpty
    @Column(name = "nombre", unique = true)
    private String nombre;

    @NotEmpty
    @Column(name = "direccion")
    private String direccion;
}
