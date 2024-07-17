package org.examen.alquiler.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
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

    @FutureOrPresent
    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @FutureOrPresent
    @Column(name = "fecha_final", nullable = false)
    private Date fechaFinal;

    @PositiveOrZero
    @Column(name = "precio_total", nullable = false)
    private Double precioTotal;

    @Column(name = "entregado", nullable = false)
    private boolean entregado;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "reserva_automovil",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "automovil_id")
    )
    @JsonIgnore
    private List<Automovil> automoviles;

    // Constructor, getters, setters y otros métodos según necesidad
}
