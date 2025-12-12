package org.sdv.proyectoviajes.repositorios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.ARepositorio;
import org.sdv.proyectoviajes.config.OracleConn;
import org.sdv.proyectoviajes.modelos.Chofer;
import org.sdv.proyectoviajes.modelos.Licencia;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosChofer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ARepositorio
public class RepositorioChoferImpl implements Repositorio<Chofer> {

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
        List<Chofer> listaChoferes = new ArrayList<>();

        try(Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P.PERSONA_ID, P.NOMBRE, P.APELLIDO_PATERNO, P.APELLIDO_MATERNO, P.TELEFONO, L.NUMERO_LICENCIA, C.ESTADO " +
                                            "FROM PERSONAS P INNER JOIN CHOFERES C ON P.PERSONA_ID = C.PERSONA_ID " +
                                            "LEFT JOIN LICENCIAS L ON C.LICENCIA_ID = L.LICENCIA_ID " +
                                            "ORDER BY PERSONA_ID")){
            while(rs.next()){
                listaChoferes.add(llenarChoferResumido(rs));
            }
        }

        return listaChoferes;
    }

    @Override
    public Chofer buscarPorId(Long id) throws SQLException {
        Chofer chofer = null;

        try(PreparedStatement stmt = conexion.prepareStatement("SELECT P.PERSONA_ID, P.NOMBRE, P.APELLIDO_PATERNO, P.APELLIDO_MATERNO, P.TELEFONO, L.LICENCIA_ID, L.NUMERO_LICENCIA, L.FECHA_VENCIMIENTO, C.COMISION, C.ESTADO " +
                                                                    "FROM PERSONAS P INNER JOIN CHOFERES C ON P.PERSONA_ID = C.PERSONA_ID " +
                                                                    "LEFT JOIN LICENCIAS L ON C.LICENCIA_ID = L.LICENCIA_ID " +
                                                                    "WHERE P.PERSONA_ID = ? ORDER BY PERSONA_ID")){
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    chofer = llenarChoferCompleto(rs);
                }
            }

        }

        return chofer;
    }


    private Chofer llenarChoferResumido(ResultSet rs) throws SQLException {
        Chofer chofer = new Chofer();
        chofer.setId(rs.getLong("PERSONA_ID"));
        chofer.setNombre(rs.getString("NOMBRE"));
        chofer.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
        chofer.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
        chofer.setTelefono(rs.getString("TELEFONO"));
        Licencia licencia = new Licencia();
        licencia.setNumeroLicencia(rs.getString("NUMERO_LICENCIA"));
        chofer.setLicencia(licencia);
        chofer.setEstado(EstadosChofer.valueOf(rs.getString("ESTADO")));
        return chofer;
    }

    private Chofer llenarChoferCompleto(ResultSet rs) throws SQLException {
        Chofer chofer = new Chofer();
        chofer.setId(rs.getLong("PERSONA_ID"));
        chofer.setNombre(rs.getString("NOMBRE"));
        chofer.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
        chofer.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
        chofer.setTelefono(rs.getString("TELEFONO"));
        Licencia licencia = new Licencia();
        licencia.setId(rs.getLong("LICENCIA_ID"));
        licencia.setNumeroLicencia(rs.getString("NUMERO_LICENCIA"));
        licencia.setFechaVencimiento(rs.getDate("FECHA_VENCIMIENTO").toLocalDate());
        chofer.setLicencia(licencia);
        chofer.setComision(rs.getInt("COMISION"));
        chofer.setEstado(EstadosChofer.valueOf(rs.getString("ESTADO")));
        return chofer;
    }
}
