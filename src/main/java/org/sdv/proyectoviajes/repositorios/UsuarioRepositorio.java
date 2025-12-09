package org.sdv.proyectoviajes.repositorios;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.OracleConn;
import org.sdv.proyectoviajes.modelos.Usuario;
import org.sdv.proyectoviajes.modelos.enumeradores.CargosUsuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UsuarioRepositorio implements RepositorioUsuarios {

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
            sqlUsuario = "INSERT INTO USUARIOS(USERNAME, EMAIL, CARGO, PERSONA_ID, PASS) VALUES (?, ?, ?, ?, ?)";
        } else{
            sqlPersona = "UPDATE PERSONAS SET NOMBRE = ?, APELLIDO_PATERNO = ?, APELLIDO_MATERNO = ?, TELEFONO = ? WHERE PERSONA_ID = ?";
            sqlUsuario = "UPDATE USUARIOS SET USERNAME = ?, EMAIL = ?, CARGO = ? WHERE PERSONA_ID = ?";
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
            stmt2.setString(2, usuario.getEmail());
            stmt2.setString(3, usuario.getCargo().toString());
            stmt2.setLong(4, usuario.getId());

            if(esNuevo){
                stmt2.setString(5, usuario.getPassword());
            }

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
    public List<Usuario> buscarTodos() throws SQLException {
        List<Usuario> listaUsuarios = new ArrayList<>();

        try(Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P.PERSONA_ID, P.NOMBRE, P.APELLIDO_PATERNO, P.APELLIDO_MATERNO, P.TELEFONO, U.USERNAME, U.EMAIL, U.CARGO " +
                                                    "FROM PERSONAS P INNER JOIN USUARIOS U ON P.PERSONA_ID = U.PERSONA_ID")){
            while(rs.next()){
                listaUsuarios.add(llenarUsuario(rs));
            }
        }

        return listaUsuarios;
    }

    @Override
    public Usuario buscarPorId(Long id) throws SQLException {

        Usuario usuario = null;

        try(PreparedStatement stmt = conexion.prepareStatement("SELECT P.PERSONA_ID, P.NOMBRE, P.APELLIDO_PATERNO, P.APELLIDO_MATERNO, P.TELEFONO, U.USERNAME, U.EMAIL, U.CARGO " +
                                                                    "FROM PERSONAS P INNER JOIN USUARIOS U ON P.PERSONA_ID = U.PERSONA_ID WHERE P.PERSONA_ID = ?")){
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    usuario = llenarUsuario(rs);
                }
            }

        }

        return usuario;
    }

    @Override
    public void cambiarPassword(long id, String nuevaPass) throws SQLException {

        try(PreparedStatement stmt = conexion.prepareStatement("UPDATE USUARIOS SET PASS = ? WHERE PERSONA_ID = ?")){

            stmt.setString(1, nuevaPass);
            stmt.setLong(2, id);
            stmt.executeUpdate();

        }

    }


    private Usuario llenarUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("PERSONA_ID"));
        usuario.setNombre(rs.getString("NOMBRE"));
        usuario.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
        usuario.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
        usuario.setTelefono(rs.getString("TELEFONO"));
        usuario.setUsername(rs.getString("USERNAME"));
        usuario.setEmail(rs.getString("EMAIL"));
        usuario.setCargo(CargosUsuario.valueOf(rs.getString("CARGO")));
        return usuario;
    }

}
