package org.sdv.proyectoviajes.repositorios;

import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T> {
    void guardar(T t) throws SQLException;
    void eliminar(Long id) throws SQLException;
    List<T> buscarTodos() throws SQLException;
    T buscarPorId(Long id) throws SQLException;
}
