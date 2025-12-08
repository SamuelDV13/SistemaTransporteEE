package org.sdv.proyectoviajes.modelos;

import jakarta.enterprise.context.RequestScoped;

import java.time.LocalDate;

@RequestScoped
public class Licencia {

    private Long id;
    private String numeroLicencia;
    private LocalDate fechaVencimiento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
