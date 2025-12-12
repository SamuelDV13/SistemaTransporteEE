package org.sdv.proyectoviajes.servicios;

import org.sdv.proyectoviajes.modelos.Licencia;
import org.sdv.proyectoviajes.modelos.Usuario;

import java.util.List;

public interface ServicioLicencias {
    void guardarLicencia(Licencia licencia);
    void eliminarLicencia(Long id);
    Licencia buscarPorIdLicencia(Long id);
}
