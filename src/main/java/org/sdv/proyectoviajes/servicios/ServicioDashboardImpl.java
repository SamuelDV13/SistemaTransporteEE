package org.sdv.proyectoviajes.servicios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.AServicio;
import org.sdv.proyectoviajes.dto.DashboardDto;
import org.sdv.proyectoviajes.excepciones.ServicioException;
import org.sdv.proyectoviajes.repositorios.RepositorioCamion;
import org.sdv.proyectoviajes.repositorios.RepositorioChoferes;
import org.sdv.proyectoviajes.repositorios.RepositorioViaje;

import java.math.BigDecimal;
import java.sql.SQLException;

@AServicio
public class ServicioDashboardImpl implements ServicioDashboard {

    @Inject
    private RepositorioViaje repositorioViaje;

    @Inject
    private RepositorioChoferes repositorioChoferes;

    @Inject
    private RepositorioCamion repositorioCamion;

    @Override
    public DashboardDto obtenerMetricas() {

        DashboardDto dashboardDto = new DashboardDto();

        try {
            dashboardDto.setViajesEnRuta(repositorioViaje.contarPorEstado("EN_PROCESO"));
            dashboardDto.setCamionesDisponibles(repositorioCamion.contarPorEstado("DISPONIBLE"));
            dashboardDto.setChoferesLibres(repositorioChoferes.contarPorEstado("DISPONIBLE"));

            BigDecimal dinero = repositorioViaje.obtenerGananciasMensuales();
            dashboardDto.setGananciasMes((dinero != null) ? dinero : BigDecimal.ZERO);

        }catch(SQLException e){
            throw new ServicioException(e.getMessage());
        }

        return dashboardDto;
    }
}
