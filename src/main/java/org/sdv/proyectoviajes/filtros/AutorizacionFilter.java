package org.sdv.proyectoviajes.filtros;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.sdv.proyectoviajes.modelos.Usuario;
import org.sdv.proyectoviajes.modelos.enumeradores.CargosUsuario;

import static org.sdv.proyectoviajes.modelos.enumeradores.CargosUsuario.*;

import java.io.IOException;

@WebFilter("/*")
public class AutorizacionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession sesion = request.getSession(false);

        if(sesion == null){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String rutaSolicitada = request.getServletPath();

        if(rutaSolicitada.startsWith("/login")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");

        if(usuario == null){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        CargosUsuario cargo = usuario.getCargo();

        boolean accesoPermitido = false;

        switch(cargo){
            case ADMINISTRADOR:
                accesoPermitido = true;
                break;

            case LOGISTICA:
                if(!rutaSolicitada.startsWith("/usuarios") && !rutaSolicitada.startsWith("/camiones")){
                    accesoPermitido = true;
                }
                break;

            case MANTENIMIENTO:
                if(!rutaSolicitada.startsWith("/usuarios") && !rutaSolicitada.startsWith("/choferes") && !rutaSolicitada.startsWith("/viajes") ){
                    accesoPermitido = true;
                }
                break;
        }

        if(accesoPermitido){
            filterChain.doFilter(servletRequest, servletResponse);
        } else{
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "No tienes acceso a esta pagina.");
        }

    }
}
