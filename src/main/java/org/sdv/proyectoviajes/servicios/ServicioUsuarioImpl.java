package org.sdv.proyectoviajes.servicios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.AServicio;
import org.sdv.proyectoviajes.excepciones.ServicioException;
import org.sdv.proyectoviajes.modelos.Usuario;
import org.sdv.proyectoviajes.repositorios.RepositorioUsuarios;

import java.sql.SQLException;
import java.util.List;

@AServicio
public class ServicioUsuarioImpl implements ServicioUsuarios{

    @Inject
    RepositorioUsuarios repositorioUsuarios;

    @Override
    public void guardarUsuario(Usuario usuario) {
        try {
            repositorioUsuarios.guardar(usuario);
        } catch (SQLException e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public void eliminarUsuario(Long id) {
        try {
            repositorioUsuarios.eliminar(id);
        } catch (SQLException e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public List<Usuario> buscarTodosUsuarios() {
        try {
            return repositorioUsuarios.buscarTodos();
        } catch (SQLException e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public Usuario buscarPorIdUsuario(Long id) {
        try {
            return repositorioUsuarios.buscarPorId(id);
        } catch (SQLException e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public void cambiarPasswordUsuario(Long id, String nuevoPassword) {
        try {
            repositorioUsuarios.cambiarPassword(id, nuevoPassword);
        } catch (SQLException e) {
            throw new ServicioException(e.getMessage());
        }
    }
}
