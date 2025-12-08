package org.sdv.proyectoviajes.config;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
public class ProducerResources {

    @Resource(name = "jdbc/oracleDB")
    private DataSource ds;

    @Produces
    @OracleConn
    private Connection getbeanConnection() throws SQLException {
        return ds.getConnection();
    }

    public void cerrarConexion(@Disposes @OracleConn Connection connection) throws SQLException {
        connection.close();
    }

}
