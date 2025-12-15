package org.sdv.proyectoviajes.modelos;

import jakarta.enterprise.context.RequestScoped;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosViaje;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequestScoped
public class Viaje {

    private Long id;
    private Camion camion;
    private Chofer chofer;
    private String origen;
    private String destino;
    private LocalDate fechaSalida;
    private LocalDate fechaEstimada;
    private LocalDate fechaEntrega;
    private EstadosViaje estado;
    private BigDecimal costo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDate getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(LocalDate fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public EstadosViaje getEstado() {
        return estado;
    }

    public void setEstado(EstadosViaje estado) {
        this.estado = estado;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

}
