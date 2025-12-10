package org.sdv.proyectoviajes.modelos;

import jakarta.enterprise.context.RequestScoped;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosChofer;

@RequestScoped
public class Chofer extends Persona{

    private int comision;
    private Licencia licencia;
    private EstadosChofer estado;

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

    public EstadosChofer getEstado() {
        return estado;
    }

    public void setEstado(EstadosChofer estado) {
        this.estado = estado;
    }
}
