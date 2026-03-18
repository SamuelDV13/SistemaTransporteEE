package org.sdv.proyectoviajes.controladores;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sdv.proyectoviajes.dto.DashboardDto;
import org.sdv.proyectoviajes.servicios.ServicioDashboard;

import java.io.IOException;

@WebServlet({"/dashboard", "/"})
public class DashBoardServlet extends HttpServlet {

    @Inject
    private ServicioDashboard servicioDashboard;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DashboardDto kpis = servicioDashboard.obtenerMetricas();

        req.setAttribute("kpis", kpis);

        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
}
