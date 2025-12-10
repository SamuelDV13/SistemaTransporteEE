package org.sdv.proyectoviajes.interceptores;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.sdv.proyectoviajes.config.OracleConn;
import org.sdv.proyectoviajes.excepciones.ServicioException;

import java.sql.Connection;

@Interceptor
@TransaccionJdbc
public class TransaccionInterceptor {

    @Inject
    @OracleConn
    Connection conexion;

    @AroundInvoke
    public Object obtenerTransaccion(InvocationContext invocationContext) throws Exception {

        if(conexion.getAutoCommit()){
            conexion.setAutoCommit(false);
        }

        try{
            Object resultado = invocationContext.proceed();
            conexion.commit();
            return resultado;
        }catch(Exception e){
            conexion.rollback();
            throw new ServicioException(e.getMessage());
        }
    }

}
