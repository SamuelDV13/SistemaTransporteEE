package org.sdv.proyectoviajes.dto;

import java.math.BigDecimal;

public class DashboardDto {

    private int viajesEnRuta;
    private int camionesDisponibles;
    private int choferesLibres;
    private BigDecimal gananciasMes;

    public int getViajesEnRuta() {
        return viajesEnRuta;
    }

    public void setViajesEnRuta(int viajesEnRuta) {
        this.viajesEnRuta = viajesEnRuta;
    }

    public int getCamionesDisponibles() {
        return camionesDisponibles;
    }

    public void setCamionesDisponibles(int camionesDisponibles) {
        this.camionesDisponibles = camionesDisponibles;
    }

    public int getChoferesLibres() {
        return choferesLibres;
    }

    public void setChoferesLibres(int choferesLibres) {
        this.choferesLibres = choferesLibres;
    }

    public BigDecimal getGananciasMes() {
        return gananciasMes;
    }

    public void setGananciasMes(BigDecimal gananciasMes) {
        this.gananciasMes = gananciasMes;
    }
}
