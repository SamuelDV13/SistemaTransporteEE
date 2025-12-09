package org.sdv.proyectoviajes.servicios;

import org.sdv.proyectoviajes.modelos.Usuario;

import java.util.List;

public interface ServicioUsuarios {
    void guardarUsuario(Usuario usuario);
    void eliminarUsuario(Long id);
    List<Usuario> buscarTodosUsuarios();
    Usuario buscarPorIdUsuario(Long id);
    void cambiarPasswordUsuario(Long id, String nuevoPassword);
}
