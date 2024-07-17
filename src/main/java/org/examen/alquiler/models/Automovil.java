package org.examen.alquiler.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;


import java.util.List;


@Data
@Entity
@Table(name = "automoviles")
public class Automovil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long automovilId;

    @NotEmpty
    @Column(name = "matricula", unique = true, nullable = false)
    private String matricula;

    @NotEmpty
    @Column(name = "modelo", nullable = false)
    private String modelo;

    @NotEmpty
    @Column(name = "color", nullable = false)
    private String color;

    @NotEmpty
    @Column(name = "marca", nullable = false)
    private String marca;

    @NotEmpty
    @Column(name = "estado")
    private String estado;

    @NotEmpty
    @Column(name = "galones")
    private Integer galones;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "garaje_id", nullable = false)
    private Garaje garaje;

    @PositiveOrZero
    @NotEmpty
    @Column(name = "precio_por_dia")
    private Double precioPorDia;

    @NotEmpty
    @ManyToMany(mappedBy = "automoviles", fetch = FetchType.EAGER)
    @JsonIgnore
    //@JsonBackReference
    //@JsonIgnoreProperties("reservas")
    private List<Reserva> reservas;

}