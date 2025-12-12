package org.sdv.proyectoviajes.servicios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.AServicio;
import org.sdv.proyectoviajes.excepciones.ServicioException;
import org.sdv.proyectoviajes.modelos.Chofer;
import org.sdv.proyectoviajes.repositorios.Repositorio;

import java.sql.SQLException;
import java.util.List;

@AServicio
public class ServicioChoferImpl implements ServicioChoferes{

    @Inject
    private Repositorio<Chofer> choferRepositorio;

    @Override
    public void guardarChofer(Chofer chofer) {
        try{
            choferRepositorio.guardar(chofer);
        }catch (SQLException e){
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public void eliminarChofer(Long id) {
        try{
            choferRepositorio.eliminar(id);
        }catch (SQLException e){
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public List<Chofer> buscarTodosChoferes() {
        try{
            return choferRepositorio.buscarTodos();
        }catch (SQLException e){
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public Chofer buscarPorIdChofer(Long id) {
        try{
            return choferRepositorio.buscarPorId(id);
        }catch (SQLException e){
            throw new ServicioException(e.getMessage());
        }
    }
}
