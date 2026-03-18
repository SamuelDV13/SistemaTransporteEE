package org.sdv.proyectoviajes.repositorios;

import org.sdv.proyectoviajes.modelos.Licencia;

import java.sql.SQLException;

public interface RepositorioLicencias extends Repositorio<Licencia> {

    int contarLicenciasPorVencer(int dias) throws SQLException;

}
