package org.sdv.proyectoviajes.servicios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.AServicio;
import org.sdv.proyectoviajes.excepciones.ServicioException;
import org.sdv.proyectoviajes.modelos.Chofer;
import org.sdv.proyectoviajes.repositorios.Repositorio;

import java.sql.SQLException;
import java.util.List;

@AServicio
public class ServicioChoferImpl implements ServicioChoferes {

    @Inject
    private Repositorio<Chofer> choferRepositorio;

    @Inject
    private ServicioLicencias servicioLicencias;

    @Override
    public void guardarChofer(Chofer chofer) {
        try {
            if (chofer.getLicencia() != null) {
                servicioLicencias.guardarLicencia(chofer.getLicencia());
            }
            choferRepositorio.guardar(chofer);
        } catch (SQLException e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public void eliminarChofer(Long id) {
        try {

            Chofer chofer = choferRepositorio.buscarPorId(id);
            choferRepositorio.eliminar(id);

            if (chofer.getLicencia() != null && chofer.getLicencia().getId() != null) {
                servicioLicencias.eliminarLicencia(chofer.getLicencia().getId());
            }

        } catch (SQLException e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public List<Chofer> buscarTodosChoferes() {
        try {
            return choferRepositorio.buscarTodos();
        } catch (SQLException e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public Chofer buscarPorIdChofer(Long id) {
        try {
            return choferRepositorio.buscarPorId(id);
        } catch (SQLException e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public void desvincularLicencia(Long id) {
        try {
            Chofer chofer = choferRepositorio.buscarPorId(id);

            if(chofer != null && chofer.getLicencia() != null) {
                long licencia_id_borrar = chofer.getLicencia().getId();
                chofer.setLicencia(null);
                choferRepositorio.guardar(chofer);
                servicioLicencias.eliminarLicencia(licencia_id_borrar);
            }

        } catch (SQLException e) {
            throw new ServicioException(e.getMessage());
        }

    }
}
