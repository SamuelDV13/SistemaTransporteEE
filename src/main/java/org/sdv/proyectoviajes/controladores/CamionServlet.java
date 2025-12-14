package org.sdv.proyectoviajes.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sdv.proyectoviajes.modelos.Camion;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosCamion;
import org.sdv.proyectoviajes.servicios.ServicioCamion;

import java.io.IOException;

@WebServlet("/camiones")
public class CamionServlet extends HttpServlet {

    private ServicioCamion servicioCamion;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accion = req.getParameter("accion");

        if(accion == null){
            accion = "listar";
        }

        switch (accion) {
            case "nuevo" -> mostrarFormulario(req, resp);
            case "editar" -> mostrarFormularioEditar(req, resp);
            case "eliminar" -> eliminarCamion(req, resp);
            case "listar" -> listarCamiones(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accion = req.getParameter("accion");

        if("guardar".equals(accion)){
            guardarCamiones(req, resp);
        } else{
            listarCamiones(req, resp);
        }

    }


    //METODOS PRIVADOS PARA LAS PETICIONES GET Y POST
    private void listarCamiones(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("camiones", servicioCamion.buscarTodosCamiones());
        req.getRequestDispatcher("/camiones/lista.jsp").forward(req, resp);
    }

    private void mostrarFormulario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/camiones/formulario.jsp").forward(req, resp);
    }

    private void mostrarFormularioEditar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Camion camion = servicioCamion.buscarPorIdCamion(id);
        req.setAttribute("camion", camion);
        req.getRequestDispatcher("/camiones/formulario.jsp").forward(req, resp);
    }

    private void guardarCamiones(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        String placa = req.getParameter("placa");
        String modelo = req.getParameter("modelo");

        double capacidad;
        try {
            capacidad = Double.parseDouble(req.getParameter("capacidad"));
        }catch (NumberFormatException e){
            capacidad = 0D;
        }

        String estado = req.getParameter("estado");


        Camion camion = new Camion();
        camion.setId(id);
        camion.setPlaca(placa);
        camion.setModelo(modelo);
        camion.setCapacidad(capacidad);

        try {
            camion.setEstado(EstadosCamion.valueOf(estado));
        }catch (Exception e){
            camion.setEstado(EstadosCamion.DISPONIBLE);
        }

        servicioCamion.guardarCamion(camion);

        resp.sendRedirect("camiones");

    }

    private void eliminarCamion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        servicioCamion.eliminarCamion(id);
        resp.sendRedirect("camiones");
    }

}
