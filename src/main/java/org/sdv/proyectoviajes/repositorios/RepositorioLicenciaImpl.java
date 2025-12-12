package org.sdv.proyectoviajes.repositorios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.OracleConn;
import org.sdv.proyectoviajes.modelos.Licencia;

import java.sql.*;
import java.util.List;

public class RepositorioLicenciaImpl implements Repositorio<Licencia> {

    @Inject
    @OracleConn
    private Connection conexion;

    @Override
    public void guardar(Licencia licencia) throws SQLException {

        String sqlLicencia;
        boolean esNuevo = licencia.getId() == null || licencia.getId() <= 0;

        String[] columnas_id = {"LICENCIA_ID"};

        if (esNuevo) {
            sqlLicencia = "INSERT INTO LICENCIAS (NUMERO_LICENCIA, FECHA_VENCIMIENTO)  VALUES (?,?)";
        } else {
            sqlLicencia = "UPDATE LICENCIAS SET NUMERO_LICENCIA = ?, FECHA_VENCIMIENTO = ? WHERE LICENCIA_ID = ?";
        }

        PreparedStatement stmt;
        if (esNuevo) {
            stmt = conexion.prepareStatement(sqlLicencia, columnas_id);
        } else {
            stmt = conexion.prepareStatement(sqlLicencia);
        }

        try (stmt) {
            stmt.setString(1, licencia.getNumeroLicencia());
            stmt.setDate(2, Date.valueOf(licencia.getFechaVencimiento()));

            if (!esNuevo) {
                stmt.setLong(3, licencia.getId());
            }

            stmt.executeUpdate();

            if (esNuevo) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        long id_generado = rs.getLong(1);
                        licencia.setId(id_generado);
                    }
                }
            }

        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {

        try(PreparedStatement stmt = conexion.prepareStatement("DELETE FROM LICENCIAS WHERE LICENCIA_ID = ?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }

    }

    @Override
    public List<Licencia> buscarTodos() throws SQLException {
        return List.of();
    }

    @Override
    public Licencia buscarPorId(Long id) throws SQLException {
        return null;
    }
}
