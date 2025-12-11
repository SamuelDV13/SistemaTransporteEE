package org.sdv.proyectoviajes.controladores;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sdv.proyectoviajes.modelos.Chofer;
import org.sdv.proyectoviajes.modelos.Licencia;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosChofer;
import org.sdv.proyectoviajes.servicios.ServicioChoferes;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/choferes")
public class ChoferServlet extends HttpServlet {

    @Inject
    private ServicioChoferes servicioChoferes;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accion = req.getParameter("accion");

        if(accion == null){
            accion = "listar";
        }

        switch (accion) {
            case "nuevo" -> mostrarFormulario(req, resp);
            case "editar" -> mostrarFormularioEditar(req, resp);
            case "eliminar" -> eliminarChofer(req, resp);
            case "listar" -> listarChoferes(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accion = req.getParameter("accion");

        if("guardar".equals(accion)){
            guardarChoferes(req, resp);
        } else{
            listarChoferes(req, resp);
        }

    }


    //METODOS PRIVADOS PARA LAS PETICIONES GET Y POST
    private void listarChoferes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("choferes", servicioChoferes.buscarTodosChoferes());
        req.getRequestDispatcher("/choferes/lista.jsp").forward(req, resp);
    }

    private void mostrarFormulario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/choferes/formulario.jsp").forward(req, resp);
    }

    private void mostrarFormularioEditar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Chofer chofer = servicioChoferes.buscarPorIdChofer(id);
        req.setAttribute("chofer", chofer);
        req.getRequestDispatcher("/choferes/formulario.jsp").forward(req, resp);
    }

    private void guardarChoferes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        String nombre = req.getParameter("nombre");
        String apellidoPaterno = req.getParameter("apellidoPaterno");
        String apellidoMaterno = req.getParameter("apellidoMaterno");
        String telefono = req.getParameter("telefono");
        Long licencia_id = Long.valueOf(req.getParameter("licencia_id"));
        String numero_licencia = req.getParameter("numero_licencia");
        String fecha_vencimiento = req.getParameter("fecha_vencimiento");
        int comision = Integer.parseInt(req.getParameter("comision"));
        String estado = req.getParameter("estado");

        Chofer chofer = new Chofer();
        chofer.setId(id);
        chofer.setNombre(nombre);
        chofer.setApellidoPaterno(apellidoPaterno);
        chofer.setApellidoMaterno(apellidoMaterno);
        chofer.setTelefono(telefono);

        Licencia licencia = new  Licencia();
        licencia.setId(licencia_id);
        licencia.setNumeroLicencia(numero_licencia);
        licencia.setFechaVencimiento(LocalDate.parse(fecha_vencimiento));

        chofer.setLicencia(licencia);
        chofer.setComision(comision);
        chofer.setEstado(EstadosChofer.valueOf(estado));

        servicioChoferes.guardarChofer(chofer);

        resp.sendRedirect("choferes");

    }

    private void eliminarChofer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        servicioChoferes.eliminarChofer(id);
        resp.sendRedirect("choferes");
    }

}
