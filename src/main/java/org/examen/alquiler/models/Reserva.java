package org.examen.alquiler.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long reservaId;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "reserva_automovil",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "automovil_id")
    )
    @JsonManagedReference
    private List<Automovil> automoviles;

    // Constructor, getters, setters y otros métodos según necesidad
}
