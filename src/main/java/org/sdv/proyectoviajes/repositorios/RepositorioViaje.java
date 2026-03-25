package org.sdv.proyectoviajes.repositorios;

import org.sdv.proyectoviajes.dto.ViajeComisionDto;
import org.sdv.proyectoviajes.modelos.Viaje;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface RepositorioViaje extends Repositorio<Viaje> {

    int contarPorEstado(String estado) throws SQLException;
    BigDecimal obtenerGananciasMensuales() throws SQLException;
    List<ViajeComisionDto> obtenerViajeYComisionPorChofer(Long idChofer) throws SQLException;
}
