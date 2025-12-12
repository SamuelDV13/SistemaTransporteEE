package org.sdv.proyectoviajes.servicios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.AServicio;
import org.sdv.proyectoviajes.excepciones.ServicioException;
import org.sdv.proyectoviajes.modelos.Licencia;
import org.sdv.proyectoviajes.repositorios.Repositorio;

import java.sql.SQLException;

@AServicio
public class ServicioLicenciaImpl implements ServicioLicencias{

    @Inject
    Repositorio<Licencia> repositorioLicencia;

    @Override
    public void guardarLicencia(Licencia licencia) {
        try{
            repositorioLicencia.guardar(licencia);
        }catch (SQLException e){
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public void eliminarLicencia(Long id) {
        try{
            repositorioLicencia.eliminar(id);
        }catch (SQLException e){
            throw new ServicioException(e.getMessage());
        }
    }

    @Override
    public Licencia buscarPorIdLicencia(Long id) {
        try{
            return repositorioLicencia.buscarPorId(id);
        }catch (SQLException e){
            throw new ServicioException(e.getMessage());
        }
    }
}
