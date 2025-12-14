package org.sdv.proyectoviajes.servicios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.AServicio;
import org.sdv.proyectoviajes.excepciones.ServicioException;
import org.sdv.proyectoviajes.modelos.Camion;
import org.sdv.proyectoviajes.repositorios.Repositorio;

import java.sql.SQLException;
import java.util.List;

@AServicio
public class ServicioCamionImpl implements ServicioCamion {

    @Inject
    private Repositorio<Camion> repositorioCamion;

    @Override
    public void guardarCamion(Camion camion) {
        try{
            repositorioCamion.guardar(camion);
        }catch (SQLException e){
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public void eliminarCamion(Long id) {
        try{
            repositorioCamion.eliminar(id);
        }catch (SQLException e){
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public List<Camion> buscarTodosCamiones() {
        try{
            return repositorioCamion.buscarTodos();
        }catch (SQLException e){
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public Camion buscarPorIdCamion(Long id) {
        try{
            return repositorioCamion.buscarPorId(id);
        }catch (SQLException e){
            throw new ServicioException(e.getMessage());
        }
    }
}
