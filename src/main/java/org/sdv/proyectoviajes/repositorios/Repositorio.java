package org.sdv.proyectoviajes.repositorios;

import java.sql.SQLException;

public interface Repositorio<T> {
    void guardar(T t) throws SQLException;
    void eliminar(Long id) throws SQLException;
    void listar() throws SQLException;

}
