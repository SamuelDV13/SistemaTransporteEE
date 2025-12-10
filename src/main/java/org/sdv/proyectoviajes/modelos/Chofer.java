package org.sdv.proyectoviajes.modelos;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class Chofer extends Persona{

    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }

    public Licencia getLicencia() {
        return licencia;
    }

    public void setLicencia(Licencia licencia) {
        this.licencia = licencia;
    }

    private int comision;
    private Licencia licencia;
}
