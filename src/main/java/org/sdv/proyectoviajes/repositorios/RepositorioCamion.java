package org.sdv.proyectoviajes.repositorios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.OracleConn;
import org.sdv.proyectoviajes.modelos.Camion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RepositorioCamion implements Repositorio<Camion>{

    @Inject
    @OracleConn
    private Connection conexion;

    @Override
    public void guardar(Camion camion) throws SQLException {

        String sqlChofer;
        boolean esNuevo = camion.getId() != null || camion.getId() <= 0;

        if(esNuevo){
            sqlChofer = "INSERT INTO CAMIONES (PLACA, MODELO, CAPACIDAD, ESTADO) VALUES (?, ?, ?, ?)";
        } else{
            sqlChofer = "UPDATE CAMIONES SET PLACA = ?, MODELO = ?, CAPACIDAD = ?, ESTADO = ? WHERE CAMION_ID = ?";
        }

        try(PreparedStatement stmt = conexion.prepareStatement(sqlChofer)){

            stmt.setString(1, camion.getPlaca());
            stmt.setString(2, camion.getModelo());
            stmt.setDouble(3, camion.getCapacidad());
            stmt.setString(4, camion.getEstado().toString());

            if (!esNuevo) {
                stmt.setLong(5, camion.getId());
            }

            stmt.executeUpdate();

        }

    }

    @Override
    public void eliminar(Long id) throws SQLException {

        try(PreparedStatement stmt = conexion.prepareStatement("DELETE CAMIONES WHERE CAMION_ID = ?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }

    }

    @Override
    public List<Camion> buscarTodos() throws SQLException {
        return List.of();
    }

    @Override
    public Camion buscarPorId(Long id) throws SQLException {
        return null;
    }
}
