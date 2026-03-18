package org.sdv.proyectoviajes.repositorios;

import org.sdv.proyectoviajes.modelos.Chofer;

import java.sql.SQLException;

public interface RepositorioChoferes extends Repositorio<Chofer> {

    int contarPorEstado(String estado) throws SQLException;

}
