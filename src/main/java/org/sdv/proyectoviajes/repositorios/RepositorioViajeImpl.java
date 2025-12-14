package org.sdv.proyectoviajes.repositorios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.OracleConn;
import org.sdv.proyectoviajes.modelos.Viaje;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RepositorioViajeImpl implements Repositorio<Viaje>{

    @Inject
    @OracleConn
    private Connection conexion;

    @Override
    public void guardar(Viaje viaje) throws SQLException {
        String sqlViaje;
        boolean esNuevo = viaje.getId() == null ||  viaje.getId() <= 0;

        if(esNuevo){
            sqlViaje = "INSERT INTO VIAJES (CAMION_ID, CHOFER_ID, ORIGEN, DESTINO, FECHA_SALIDA, FECHA_ENTREGA, ESTADO, COSTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        } else{
            sqlViaje = "UPDATE VIAJES SET CAMION_ID = ?, CHOFER_ID = ?, ORIGEN = ?, DESTINO = ?, FECHA_SALIDA = ?, FECHA_ENTREGA = ?, ESTADO = ?, COSTO = ? WHERE VIAJE_ID = ?";
        }

        try(PreparedStatement stmt = conexion.prepareStatement(sqlViaje)){

            stmt.setLong(1, viaje.getCamion().getId());
            stmt.setLong(2, viaje.getChofer().getId());
            stmt.setString(3,  viaje.getOrigen());
            stmt.setString(4,  viaje.getDestino());
            stmt.setDate(5, Date.valueOf(viaje.getFechaSalida()));
            stmt.setDate(6, Date.valueOf(viaje.getFechaEntrega()));
            stmt.setString(7, viaje.getEstado().toString());
            stmt.setBigDecimal(8, viaje.getCosto());

            if(!esNuevo){
                stmt.setLong(9, viaje.getId());
            }

            stmt.executeUpdate();

        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    @Override
    public List<Viaje> buscarTodos() throws SQLException {
        return List.of();
    }

    @Override
    public Viaje buscarPorId(Long id) throws SQLException {
        return null;
    }
}
