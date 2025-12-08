package org.sdv.proyectoviajes.repositorios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.OracleConn;
import org.sdv.proyectoviajes.modelos.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepositorio implements Repositorio<Usuario> {

    @Inject
    @OracleConn
    Connection conexion;

    @Override
    public void guardar(Usuario usuario) throws SQLException {
        String sqlPersona;
        String sqlUsuario;

        String[] columnasId = {"PERSONA_ID"};

        boolean esNuevo = usuario.getId() == null || usuario.getId() <= 0;

        if(esNuevo){
            sqlPersona = "INSERT INTO PERSONAS(NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO,  TELEFONO) VALUES (?, ?, ?, ?)";
            sqlUsuario = "INSERT INTO USUARIOS(USERNAME, PASS, EMAIL, CARGO, PERSONA_ID) VALUES (?, ?, ?, ?, ?)";
        } else{
            sqlPersona = "UPDATE PERSONAS SET NOMBRE = ?, APELLIDO_PATERNO = ?, APELLIDO_MATERNO = ?, TELEFONO = ? WHERE PERSONA_ID = ?";
            sqlUsuario = "UPDATE USUARIOS SET USERNAME = ?, PASS = ?, EMAIL = ?, CARGO = ? WHERE PERSONA_ID = ?";
        }

        PreparedStatement stmt;
        if (esNuevo) {
            stmt = conexion.prepareStatement(sqlPersona, columnasId);
        } else {
            stmt = conexion.prepareStatement(sqlPersona);
        }

        try(stmt){
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellidoPaterno());
            stmt.setString(3, usuario.getApellidoMaterno());
            stmt.setString(4, usuario.getTelefono());

            if(!esNuevo){
                stmt.setLong(5, usuario.getId());
            }

            stmt.executeUpdate();

            if(esNuevo){

                try(ResultSet rs = stmt.getGeneratedKeys()){
                    if(rs.next()){
                        long id_generado = rs.getLong(1);
                        usuario.setId(id_generado);
                    }
                }
            }
        }

        try(PreparedStatement stmt2 = conexion.prepareStatement(sqlUsuario)){
            stmt2.setString(1, usuario.getUsername());
            stmt2.setString(2, usuario.getPassword());
            stmt2.setString(3, usuario.getEmail());
            stmt2.setString(4, usuario.getCargo().toString());
            stmt2.setLong(5, usuario.getId());

            stmt2.executeUpdate();
        }

    }

    @Override
    public void eliminar(Long id) throws SQLException {

        try(PreparedStatement stmt = conexion.prepareStatement("DELETE FROM USUARIOS WHERE PERSONA_ID = ?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }

        try(PreparedStatement stmt = conexion.prepareStatement("DELETE FROM PERSONAS WHERE PERSONA_ID = ?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }

    }

    @Override
    public void listar() throws SQLException {

    }
}
