package org.sdv.proyectoviajes.servicios;

import org.sdv.proyectoviajes.modelos.Chofer;

import java.util.List;

public interface ServicioChoferes {
    void guardarChofer(Chofer chofer);
    void eliminarChofer(Long id);
    List<Chofer> buscarTodosChoferes();
    Chofer buscarPorIdChofer(Long id);
    void desvincularLicencia(Long id);
}
