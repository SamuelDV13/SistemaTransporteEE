package org.sdv.proyectoviajes.servicios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.AServicio;
import org.sdv.proyectoviajes.dto.ViajeComisionDto;
import org.sdv.proyectoviajes.excepciones.ServicioException;
import org.sdv.proyectoviajes.modelos.Viaje;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosCamion;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosChofer;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosViaje;
import org.sdv.proyectoviajes.repositorios.Repositorio;
import org.sdv.proyectoviajes.repositorios.RepositorioCamionImpl;
import org.sdv.proyectoviajes.repositorios.RepositorioChoferImpl;
import org.sdv.proyectoviajes.repositorios.RepositorioViajeImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@AServicio
public class ServicioViajeImpl implements ServicioViajes {

    @Inject
    private RepositorioViajeImpl viajeRepositorio;

    @Inject
    private RepositorioCamionImpl camionRepositorio;

    @Inject
    private RepositorioChoferImpl choferRepositorio;
    @Inject
    private RepositorioViajeImpl repositorioViajeImpl;

    @Override
    public void guardarViaje(Viaje viaje, long idCamionAnterior, long idChoferAnterior) {
        try {

            boolean viajeCompleto = viaje.getEstado() == EstadosViaje.COMPLETADO;

            if(viajeCompleto){
                viaje.setFechaEntrega(LocalDate.now());
            }

            viajeRepositorio.guardar(viaje);


            if(idCamionAnterior > 0 && viaje.getCamion().getId() != idCamionAnterior){
                camionRepositorio.actualizarEstado(idCamionAnterior, EstadosCamion.DISPONIBLE.toString());
            }

            if(idChoferAnterior > 0 && viaje.getChofer().getId() != idChoferAnterior){
                choferRepositorio.actualizarEstado(idChoferAnterior, EstadosChofer.DISPONIBLE.toString());
            }


            if(viajeCompleto){
                camionRepositorio.actualizarEstado(viaje.getCamion().getId(), EstadosCamion.DISPONIBLE.toString());
                choferRepositorio.actualizarEstado(viaje.getChofer().getId(), EstadosChofer.DISPONIBLE.toString());
            } else{
                camionRepositorio.actualizarEstado(viaje.getCamion().getId(), EstadosCamion.EN_VIAJE.toString());
                choferRepositorio.actualizarEstado(viaje.getChofer().getId(), EstadosChofer.EN_VIAJE.toString());
            }

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

    @Override
    public List<ViajeComisionDto> obtenerViajeYComisionPorChofer(Long idChofer) {
        try{
            return repositorioViajeImpl.obtenerViajeYComisionPorChofer(idChofer);
        }catch (SQLException e){
            throw new ServicioException(e.getMessage());
        }
    }
}
