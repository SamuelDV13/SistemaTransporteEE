package org.sdv.proyectoviajes.modelos;

import jakarta.enterprise.context.RequestScoped;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosCamion;

@RequestScoped
public class Camion {

    private Long id;
    private String placa;
    private String modelo;
    private Double capacidad;
    private EstadosCamion estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Double capacidad) {
        this.capacidad = capacidad;
    }

    public EstadosCamion getEstado() {
        return estado;
    }

    public void setEstado(EstadosCamion estado) {
        this.estado = estado;
    }
}
