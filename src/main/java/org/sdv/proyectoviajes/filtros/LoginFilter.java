package org.sdv.proyectoviajes.filtros;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({"/dashboard", "/usuarios/*", "/viajes/*", "/camiones/*", "/choferes/*"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            HttpServletRequest req =  (HttpServletRequest) servletRequest;
            HttpServletResponse resp = (HttpServletResponse) servletResponse;

            HttpSession session = req.getSession(false);

            if(session != null && session.getAttribute("usuario") != null){
                filterChain.doFilter(servletRequest, servletResponse);
            } else{
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
            }

    }
}
