package org.sdv.proyectoviajes.repositorios;

import org.sdv.proyectoviajes.modelos.Camion;

import java.sql.SQLException;

public interface RepositorioCamion extends Repositorio<Camion>{

    int contarPorEstado(String estado) throws SQLException;

}
