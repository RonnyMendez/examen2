package org.examen.alquiler.DTO;

import java.util.Date;
import java.util.List;

public class ReservaDTO {

    private Long clienteId;
    private Date fechaInicio;
    private Date fechaFinal;
    private Double precioTotal;
    private List<Long> automoviles;

    // Constructores, getters y setters

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public List<Long> getAutomoviles() {
        return automoviles;
    }

    public void setAutomoviles(List<Long> automoviles) {
        this.automoviles = automoviles;
    }
}