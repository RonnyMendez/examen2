package org.examen.alquiler.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "garajes")
public class Garaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long garajeId;
    private String nombre;
    private String direccion;
}
