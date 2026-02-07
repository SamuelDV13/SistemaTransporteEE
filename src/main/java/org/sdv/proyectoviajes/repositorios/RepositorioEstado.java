package org.sdv.proyectoviajes.repositorios;

import java.sql.SQLException;

public interface RepositorioEstado {
    void actualizarEstado(Long id, String nuevoEstado) throws SQLException;
}
