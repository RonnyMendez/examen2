package org.examen.alquiler.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    @ManyToOne
    @JoinColumn(name = "cliente_codigo", nullable = false)
    private Cliente cliente;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "fecha_final", nullable = false)
    private Date fechaFinal;

    @Column(name = "precio_total", nullable = false)
    private Double precioTotal;

    @Column(name = "entregado", nullable = false)
    private boolean entregado;

    @ManyToMany
    @JoinTable(
            name = "reserva_automovil",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "automovil_matricula")
    )
    private List<Automovil> automoviles;

    // Constructor, getters, setters y otros métodos según necesidad
}
