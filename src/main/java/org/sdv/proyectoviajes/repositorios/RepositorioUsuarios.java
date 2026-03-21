package org.sdv.proyectoviajes.repositorios;

import org.sdv.proyectoviajes.modelos.Usuario;

import java.sql.SQLException;

public interface RepositorioUsuarios extends  Repositorio<Usuario> {

    void cambiarPassword(long id, String nuevaPass) throws SQLException;
    Usuario obtenerPorCredenciales(String username, String password) throws SQLException;
}
