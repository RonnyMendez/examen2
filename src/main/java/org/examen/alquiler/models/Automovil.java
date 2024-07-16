package org.examen.alquiler.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "automoviles")
public class Automovil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long automovilId;

    @Column(name = "matricula", unique = true, nullable = false)
    private String matricula;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "estado")
    private String estado;

    @Column(name = "galones")
    private Integer galones;

    @ManyToOne
    @JoinColumn(name = "garaje_id", nullable = false)
    private Garaje garaje;

    @Column(name = "precio_por_dia")
    private Double precioPorDia;


    @ManyToMany(mappedBy = "automoviles", fetch = FetchType.EAGER)
    @JsonIgnore
    //@JsonBackReference
    //@JsonIgnoreProperties("reservas")
    private List<Reserva> reservas;

}