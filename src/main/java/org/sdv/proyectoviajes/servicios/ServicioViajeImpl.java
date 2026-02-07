package org.sdv.proyectoviajes.servicios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.AServicio;
import org.sdv.proyectoviajes.excepciones.ServicioException;
import org.sdv.proyectoviajes.modelos.Camion;
import org.sdv.proyectoviajes.modelos.Chofer;
import org.sdv.proyectoviajes.modelos.Viaje;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosCamion;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosChofer;
import org.sdv.proyectoviajes.repositorios.Repositorio;
import org.sdv.proyectoviajes.repositorios.RepositorioCamion;
import org.sdv.proyectoviajes.repositorios.RepositorioChoferImpl;

import java.sql.SQLException;
import java.util.List;

@AServicio
public class ServicioViajeImpl implements ServicioViajes {

    @Inject
    private Repositorio<Viaje> viajeRepositorio;

    @Inject
    private RepositorioCamion camionRepositorio;

    @Inject
    private RepositorioChoferImpl choferRepositorio;

    @Override
    public void guardarViaje(Viaje viaje) {
        try {
            viajeRepositorio.guardar(viaje);
            camionRepositorio.actualizarEstado(viaje.getCamion().getId(), EstadosCamion.EN_VIAJE.toString());
            choferRepositorio.actualizarEstado(viaje.getChofer().getId(), EstadosChofer.EN_VIAJE.toString());
        } catch (SQLException e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public void eliminarViaje(Long id) {
        try {
            viajeRepositorio.eliminar(id);
        } catch (SQLException e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public List<Viaje> buscarTodosViajes() {
        try {
            return viajeRepositorio.buscarTodos();
        } catch (SQLException e) {
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public Viaje buscarPorIdViaje(Long id) {
        try {
            return viajeRepositorio.buscarPorId(id);
        } catch (SQLException e) {
            throw new ServicioException(e.getMessage());
        }
    }
}
