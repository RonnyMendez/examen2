package org.examen.alquiler.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteCodigo;

    @Length(max = 9)
    @Column(name = "DNI", unique = true)
    private String DNI;

    @NotEmpty
    @Column(name = "nombre")
    private String nombre;

    @NotEmpty
    @Column(name = "direccion")
    private String direccion;

    @NotEmpty
    @Column(name = "telefono")
    private String telefono;

}
