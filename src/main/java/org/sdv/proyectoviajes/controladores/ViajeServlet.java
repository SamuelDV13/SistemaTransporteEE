package org.sdv.proyectoviajes.controladores;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sdv.proyectoviajes.modelos.Camion;
import org.sdv.proyectoviajes.modelos.Chofer;
import org.sdv.proyectoviajes.modelos.Usuario;
import org.sdv.proyectoviajes.modelos.Viaje;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosChofer;
import org.sdv.proyectoviajes.modelos.enumeradores.EstadosViaje;
import org.sdv.proyectoviajes.servicios.ServicioCamion;
import org.sdv.proyectoviajes.servicios.ServicioChoferes;
import org.sdv.proyectoviajes.servicios.ServicioViajes;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet("/viajes")
public class ViajeServlet extends HttpServlet {

    @Inject
    ServicioViajes  servicioViajes;
    @Inject
    ServicioCamion servicioCamiones;
    @Inject
    ServicioChoferes servicioChoferes;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accion = req.getParameter("accion");

        if(accion == null){
            accion = "listar";
        }

        switch (accion) {
            case "nuevo" -> mostrarFormulario(req, resp);
            case "editar" -> mostrarFormularioEditar(req, resp);
            case "eliminar" -> cancelarViaje(req, resp);
            case "listar" -> listarViajes(req, resp);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accion = req.getParameter("accion");

        if("guardar".equals(accion)){
            guardarViajes(req, resp);
        } else{
            listarViajes(req, resp);
        }

    }


    //METODOS PRIVADOS PARA LAS PETICIONES GET Y POST
    private void listarViajes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("viajes", servicioViajes.buscarTodosViajes());
        req.getRequestDispatcher("/viajes/lista.jsp").forward(req, resp);
    }

    private void mostrarFormulario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("listaChoferes", servicioChoferes.listarChoferesParaSelect());
        req.setAttribute("listaCamiones", servicioCamiones.listarCamionesParaSelect());
        req.getRequestDispatcher("/viajes/formulario.jsp").forward(req, resp);
    }

    private void mostrarFormularioEditar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Viaje viaje = servicioViajes.buscarPorIdViaje(id);

        req.setAttribute("listaChoferes", servicioChoferes.listarChoferesParaSelect());
        req.setAttribute("listaCamiones", servicioCamiones.listarCamionesParaSelect());
        req.setAttribute("viaje", viaje);
        req.getRequestDispatcher("/viajes/formulario.jsp").forward(req, resp);
    }

    private void guardarViajes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        String origen = req.getParameter("origen");
        String destino = req.getParameter("destino");
        String fechaSalida = req.getParameter("fechaSalida");
        String fechaEstimada = req.getParameter("fechaEstimada");
        String estado = req.getParameter("estado");
        long choferId = Long.parseLong(req.getParameter("choferId"));
        long camionId = Long.parseLong(req.getParameter("camionId"));

        BigDecimal costo;
        try{
            costo = new BigDecimal(req.getParameter("costo"));
        } catch(NumberFormatException e){
            costo = new BigDecimal(0);
        }

        Viaje viaje = new Viaje();
        viaje.setId(id);
        viaje.setOrigen(origen);
        viaje.setDestino(destino);
        viaje.setFechaSalida(LocalDate.parse(fechaSalida));
        viaje.setFechaEstimada(LocalDate.parse(fechaEstimada));

        try {
            viaje.setEstado(EstadosViaje.valueOf(estado));
        }catch (Exception e){
            viaje.setEstado(EstadosViaje.EN_PREPARACION);
        }

        Chofer chofer = new Chofer();
        chofer.setId(choferId);
        viaje.setChofer(chofer);

        Camion camion = new Camion();
        camion.setId(camionId);
        viaje.setCamion(camion);

        viaje.setCosto(costo);

        servicioViajes.guardarViaje(viaje);

        resp.sendRedirect("viajes");

    }

    private void cancelarViaje(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        servicioViajes.eliminarViaje(id);
        resp.sendRedirect("viajes");
    }


    }
