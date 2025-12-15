package org.sdv.proyectoviajes.repositorios;

import jakarta.inject.Inject;
import org.sdv.proyectoviajes.config.ARepositorio;
import org.sdv.proyectoviajes.config.OracleConn;
import org.sdv.proyectoviajes.modelos.Camion;
import org.sdv.proyectoviajes.modelos.Chofer;
import org.sdv.proyectoviajes.modelos.Licencia;
import org.sdv.proyectoviajes.modelos.Viaje;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosCamion;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosChofer;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosViaje;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ARepositorio
public class RepositorioViajeImpl implements Repositorio<Viaje> {

    @Inject
    @OracleConn
    private Connection conexion;

    @Override
    public void guardar(Viaje viaje) throws SQLException {
        String sqlViaje;
        boolean esNuevo = viaje.getId() == null || viaje.getId() <= 0;

        if (esNuevo) {
            sqlViaje = "INSERT INTO VIAJES (CAMION_ID, CHOFER_ID, ORIGEN, DESTINO, FECHA_SALIDA, FECHA_ESTIMADA, ESTADO, COSTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        } else {
            sqlViaje = "UPDATE VIAJES SET CAMION_ID = ?, CHOFER_ID = ?, ORIGEN = ?, DESTINO = ?, FECHA_SALIDA = ?, FECHA_ESTIMADA = ?, ESTADO = ?, COSTO = ?, FECHA_ENTREGA = ? WHERE VIAJE_ID = ?";
        }

        try (PreparedStatement stmt = conexion.prepareStatement(sqlViaje)) {

            stmt.setLong(1, viaje.getCamion().getId());
            stmt.setLong(2, viaje.getChofer().getId());
            stmt.setString(3, viaje.getOrigen());
            stmt.setString(4, viaje.getDestino());
            stmt.setDate(5, Date.valueOf(viaje.getFechaSalida()));
            stmt.setDate(6, Date.valueOf(viaje.getFechaEstimada()));
            stmt.setString(7, viaje.getEstado().toString());
            stmt.setBigDecimal(8, viaje.getCosto());

            if (!esNuevo) {
                if(viaje.getFechaEntrega() != null){
                    stmt.setDate(9, Date.valueOf(viaje.getFechaEntrega()));
                } else{
                    stmt.setNull(9, Types.DATE);
                }

                stmt.setLong(10, viaje.getId());
            }

            stmt.executeUpdate();

        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {

        try (PreparedStatement stmt = conexion.prepareStatement("UPDATE VIAJES SET ESTADO = ? WHERE VIAJE_ID = ?")) {
            stmt.setString(1, EstadosViaje.CANCELADO.toString());
            stmt.setLong(2, id);

            stmt.executeUpdate();
        }

    }

    @Override
    public List<Viaje> buscarTodos() throws SQLException {
        List<Viaje> listaViajes = new ArrayList<>();

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT VIAJE_ID, ORIGEN, DESTINO, FECHA_SALIDA, FECHA_ESTIMADA, FECHA_ENTREGA, ESTADO FROM VIAJES ORDER BY VIAJE_ID")) {

            while (rs.next()) {
                listaViajes.add(llenarViajeResumen(rs));
            }
        }

        return listaViajes;
    }

    @Override
    public Viaje buscarPorId(Long id) throws SQLException {
        Viaje viaje = null;

        try (PreparedStatement stmt = conexion.prepareStatement("SELECT V.VIAJE_ID, V.ORIGEN, V.DESTINO, V.FECHA_SALIDA, V.FECHA_ESTIMADA, V.FECHA_ENTREGA, V.ESTADO AS ESTADO_VIAJE, V.COSTO, V.CAMION_ID, CM.PLACA, CM.MODELO, CM.CAPACIDAD, CM.ESTADO AS ESTADO_CAMION, " +
                "V.CHOFER_ID, P.NOMBRE, P.APELLIDO_PATERNO, P.APELLIDO_MATERNO, P.TELEFONO, CH.COMISION, CH.ESTADO AS ESTADO_CHOFER, CH.LICENCIA_ID, L.NUMERO_LICENCIA, L.FECHA_VENCIMIENTO " +
                "FROM VIAJES V " +
                "INNER JOIN CAMIONES CM ON V.CAMION_ID = CM.CAMION_ID " +
                "INNER JOIN CHOFERES CH ON V.CHOFER_ID = CH.PERSONA_ID " +
                "INNER JOIN PERSONAS P ON CH.PERSONA_ID = P.PERSONA_ID " +
                "LEFT JOIN LICENCIAS L ON CH.LICENCIA_ID = L.LICENCIA_ID " +
                "WHERE V.VIAJE_ID = ?")) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    viaje = llenarViajeCompleto(rs);
                }
            }

        }

        return viaje;
    }

    private Viaje llenarViajeResumen(ResultSet rs) throws SQLException {
        Viaje viaje = new Viaje();
        viaje.setId(rs.getLong("VIAJE_ID"));
        viaje.setOrigen(rs.getString("ORIGEN"));
        viaje.setDestino(rs.getString("DESTINO"));
        viaje.setFechaSalida(rs.getDate("FECHA_SALIDA").toLocalDate());
        viaje.setFechaEstimada(rs.getDate("FECHA_ESTIMADA").toLocalDate());
        Date fechaEntrega = rs.getDate("FECHA_ENTREGA");
        if(fechaEntrega != null) {
            viaje.setFechaEntrega(fechaEntrega.toLocalDate());
        }
        viaje.setEstado(EstadosViaje.valueOf(rs.getString("ESTADO")));
        return viaje;
    }

    private Viaje llenarViajeCompleto(ResultSet rs) throws SQLException {
        Viaje viaje = new Viaje();
        viaje.setId(rs.getLong("VIAJE_ID"));
        viaje.setOrigen(rs.getString("ORIGEN"));
        viaje.setDestino(rs.getString("DESTINO"));
        viaje.setFechaSalida(rs.getDate("FECHA_SALIDA").toLocalDate());
        viaje.setFechaEstimada(rs.getDate("FECHA_ESTIMADA").toLocalDate());
        Date fechaEntrega = rs.getDate("FECHA_ENTREGA");
        if(fechaEntrega != null) {
            viaje.setFechaEntrega(fechaEntrega.toLocalDate());
        }
        viaje.setEstado(EstadosViaje.valueOf(rs.getString("ESTADO_VIAJE")));
        viaje.setCosto(rs.getBigDecimal("COSTO"));

        Camion camion = new Camion();
        camion.setId(rs.getLong("CAMION_ID"));
        camion.setPlaca(rs.getString("PLACA"));
        camion.setModelo(rs.getString("MODELO"));
        camion.setCapacidad(rs.getDouble("CAPACIDAD"));
        camion.setEstado(EstadosCamion.valueOf(rs.getString("ESTADO_CAMION")));
        viaje.setCamion(camion);

        Chofer chofer = new Chofer();
        chofer.setId(rs.getLong("CHOFER_ID"));
        chofer.setNombre(rs.getString("NOMBRE"));
        chofer.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
        chofer.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
        chofer.setTelefono(rs.getString("TELEFONO"));
        chofer.setComision(rs.getInt("COMISION"));
        chofer.setEstado(EstadosChofer.valueOf(rs.getString("ESTADO_CHOFER")));

        long id_licencia = rs.getLong("LICENCIA_ID");
        if (id_licencia > 0) {
            Licencia licencia = new Licencia();
            licencia.setId(id_licencia);
            licencia.setNumeroLicencia(rs.getString("NUMERO_LICENCIA"));
            licencia.setFechaVencimiento(rs.getDate("FECHA_VENCIMIENTO").toLocalDate());
            chofer.setLicencia(licencia);
        }

        viaje.setChofer(chofer);

        return viaje;
    }
}
