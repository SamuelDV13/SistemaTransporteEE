package org.sdv.proyectoviajes.controladores;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.sdv.proyectoviajes.modelos.Usuario;
import org.sdv.proyectoviajes.servicios.ServicioUsuarios;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private ServicioUsuarios servicioUsuarios;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Usuario usuario = servicioUsuarios.obtenerPorCredenciales(username, password);

        if (usuario != null) {
            //Logica para permitir el acceso

            HttpSession session = req.getSession();
            session.setAttribute("usuarioLogueado", usuario);

            resp.sendRedirect(req.getContextPath() + "/dashboard");

        } else{
            //Logica para denegar el acceso

            req.setAttribute("error", "Usuario o contraseña incorrectos.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }
}
