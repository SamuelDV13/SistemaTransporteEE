package org.sdv.proyectoviajes.repositorios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.OracleConn;
import org.sdv.proyectoviajes.modelos.Chofer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ChoferRepositorio implements Repositorio<Chofer> {

    @Inject
    @OracleConn
    private Connection conexion;

    @Override
    public void guardar(Chofer chofer) throws SQLException {
        String sqlPersona;
        String sqlChofer;

        String[] columnasId = {"PERSONA_ID"};

        boolean esNuevo = chofer.getId() == null || chofer.getId() <= 0;

        if(esNuevo){
            sqlPersona = "INSERT INTO PERSONAS(NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO,  TELEFONO) VALUES (?, ?, ?, ?)";
            sqlChofer = "INSERT INTO CHOFERES(COMISION, LICENCIA_ID, ESTADO, PERSONA_ID) VALUES (?, ?, ?, ?)";
        } else{
            sqlPersona = "UPDATE PERSONAS SET NOMBRE = ?, APELLIDO_PATERNO = ?, APELLIDO_MATERNO = ?, TELEFONO = ? WHERE PERSONA_ID = ?";
            sqlChofer = "UPDATE CHOFERES SET COMISION = ?, LICENCIA_ID = ?, ESTADO = ? WHERE PERSONA_ID = ?";
        }

        PreparedStatement stmt;
        if (esNuevo) {
            stmt = conexion.prepareStatement(sqlPersona, columnasId);
        } else {
            stmt = conexion.prepareStatement(sqlPersona);
        }

        try(stmt){
            stmt.setString(1, chofer.getNombre());
            stmt.setString(2, chofer.getApellidoPaterno());
            stmt.setString(3, chofer.getApellidoMaterno());
            stmt.setString(4, chofer.getTelefono());

            if(!esNuevo){
                stmt.setLong(5, chofer.getId());
            }

            stmt.executeUpdate();

            if(esNuevo){

                try(ResultSet rs = stmt.getGeneratedKeys()){
                    if(rs.next()){
                        long id_generado = rs.getLong(1);
                        chofer.setId(id_generado);
                    }
                }
            }
        }

        try(PreparedStatement stmt2 = conexion.prepareStatement(sqlChofer)){
            stmt2.setInt(1, chofer.getComision());
            stmt2.setLong(2, chofer.getLicencia().getId());
            stmt2.setString(3, chofer.getEstado().toString());
            stmt2.setLong(4, chofer.getId());

            stmt2.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try(PreparedStatement stmt = conexion.prepareStatement("DELETE FROM CHOFERES WHERE PERSONA_ID = ?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }

        try(PreparedStatement stmt = conexion.prepareStatement("DELETE FROM PERSONAS WHERE PERSONA_ID = ?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Chofer> buscarTodos() throws SQLException {
        return List.of();
    }

    @Override
    public Chofer buscarPorId(Long id) throws SQLException {
        return null;
    }
}
