package org.sdv.proyectoviajes.controladores;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sdv.proyectoviajes.modelos.Usuario;
import org.sdv.proyectoviajes.modelos.enumeradores.CargosUsuario;
import org.sdv.proyectoviajes.servicios.ServicioUsuarios;

import java.io.IOException;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    @Inject
    ServicioUsuarios servicioUsuarios;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accion = req.getParameter("accion");

        if(accion == null){
            accion = "listar";
        }

        switch (accion) {
            case "nuevo" -> mostrarFormulario(req, resp);
            case "editar" -> mostrarFormularioEditar(req, resp);
            case "eliminar" -> eliminarUsuario(req, resp);
            case "listar" -> listarUsuarios(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accion = req.getParameter("accion");

        if("guardar".equals(accion)){
            guardarUsuarios(req, resp);
        } else{
            listarUsuarios(req, resp);
        }

    }


    //METODOS PRIVADOS PARA LAS PETICIONES GET Y POST
    private void listarUsuarios(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("usuarios", servicioUsuarios.buscarTodosUsuarios());
        req.getRequestDispatcher("/usuarios/lista.jsp").forward(req, resp);
    }

    private void mostrarFormulario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/usuarios/formulario.jsp").forward(req, resp);
    }

    private void mostrarFormularioEditar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Usuario usuario = servicioUsuarios.buscarPorIdUsuario(id);
        req.setAttribute("usuario", usuario);
        req.getRequestDispatcher("/usuarios/formulario.jsp").forward(req, resp);
    }

    private void guardarUsuarios(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String cargo = req.getParameter("cargo");

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setApellidoPaterno(apellidoPaterno);
        usuario.setApellidoMaterno(apellidoMaterno);
        usuario.setTelefono(telefono);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEmail(email);
        usuario.setCargo(CargosUsuario.valueOf(cargo));

        servicioUsuarios.guardarUsuario(usuario);
        resp.sendRedirect("usuarios");

    }

    private void eliminarUsuario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        servicioUsuarios.eliminarUsuario(id);
        resp.sendRedirect("usuarios");
    }

}
