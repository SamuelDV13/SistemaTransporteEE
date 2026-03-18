package org.sdv.proyectoviajes.repositorios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.ARepositorio;
import org.sdv.proyectoviajes.config.OracleConn;
import org.sdv.proyectoviajes.modelos.Licencia;

import java.sql.*;
import java.util.List;

@ARepositorio
public class RepositorioLicenciaImpl implements RepositorioLicencias {

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
        //TODO: Implementar consulta a la BD. Retorno de lista vacia para evitar errores.
        return List.of();
    }

    @Override
    public Licencia buscarPorId(Long id) throws SQLException {

        Licencia licencia = null;

        try(PreparedStatement stmt = conexion.prepareStatement("SELECT LICENCIA_ID, NUMERO_LICENCIA, FECHA_VENCIMIENTO FROM LICENCIAS WHERE LICENCIA_ID = ?")){

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    licencia = llenarLicencia(rs);
                }
            }
        }

        return licencia;

    }

    private Licencia llenarLicencia(ResultSet rs) throws SQLException {
        Licencia licencia = new Licencia();
        licencia.setId(rs.getLong("LICENCIA_ID"));
        licencia.setNumeroLicencia(rs.getString("NUMERO_LICENCIA"));
        licencia.setFechaVencimiento(rs.getDate("FECHA_VENCIMIENTO").toLocalDate());
        return licencia;
    }

    @Override
    public int contarLicenciasPorVencer(int dias) throws SQLException {

        int total = 0;

        try(PreparedStatement stmt = conexion.prepareStatement("SELECT COUNT(*) AS TOTAL FROM LICENCIAS WHERE FECHA_VENCIMIENTO < SYSDATE + ?")){

            stmt.setInt(1, dias);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    total = rs.getInt("TOTAL");
                }
            }
        }
        return total;
    }
}
