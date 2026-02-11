package org.sdv.proyectoviajes.repositorios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.ARepositorio;
import org.sdv.proyectoviajes.config.OracleConn;
import org.sdv.proyectoviajes.dto.ObjetoSelectDto;
import org.sdv.proyectoviajes.modelos.Camion;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosCamion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ARepositorio
public class RepositorioCamionImpl implements Repositorio<Camion>, RepositorioSelectable, RepositorioEstado{

    @Inject
    @OracleConn
    private Connection conexion;

    @Override
    public void guardar(Camion camion) throws SQLException {

        String sqlChofer;
        boolean esNuevo = camion.getId() == null || camion.getId() <= 0;

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

        List<Camion> listaCamiones = new ArrayList<>();

        try(Statement stmt = conexion.createStatement();
            ResultSet rs =  stmt.executeQuery("SELECT CAMION_ID, PLACA, MODELO, CAPACIDAD, ESTADO FROM CAMIONES")){

            while(rs.next()){
                listaCamiones.add(llenarCamion(rs));
            }

        }

        return listaCamiones;

    }

    @Override
    public Camion buscarPorId(Long id) throws SQLException {

        Camion camion = null;

        try(PreparedStatement stmt = conexion.prepareStatement("SELECT CAMION_ID, PLACA, MODELO, CAPACIDAD, ESTADO FROM CAMIONES WHERE CAMION_ID = ?")){
            stmt.setLong(1, id);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    camion = llenarCamion(rs);
                }
            }

        }

        return camion;

    }

    private Camion llenarCamion(ResultSet rs) throws SQLException {
        Camion camion = new Camion();
        camion.setId(rs.getLong("CAMION_ID"));
        camion.setPlaca(rs.getString("PLACA"));
        camion.setModelo(rs.getString("MODELO"));
        camion.setCapacidad(rs.getDouble("CAPACIDAD"));
        camion.setEstado(EstadosCamion.valueOf(rs.getString("ESTADO")));
        return camion;
    }

    @Override
    public List<ObjetoSelectDto> listarParaSelect() throws SQLException {

        List<ObjetoSelectDto> listaCamiones = new ArrayList<>();

        try(Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT CAMION_ID, MODELO || ' - ' || PlACA || ' - ' || CAPACIDAD || 'ton' AS TEXTO_OPCION " +
                                                "FROM CAMIONES " +
                                                "WHERE ESTADO = 'DISPONIBLE'")){

            while(rs.next()){
                ObjetoSelectDto objetoSelect = new ObjetoSelectDto(rs.getLong("CAMION_ID"), rs.getString("TEXTO_OPCION"));
                listaCamiones.add(objetoSelect);
            }
        }

        return listaCamiones;

    }

    @Override
    public void actualizarEstado(Long id, String nuevoEstado) throws SQLException {

        try(PreparedStatement stmt = conexion.prepareStatement("UPDATE CAMIONES SET ESTADO = ? WHERE CAMION_ID = ?")){

            stmt.setString(1, nuevoEstado);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }
}
