package org.sdv.proyectoviajes.modelos;

import jakarta.enterprise.context.RequestScoped;
import org.sdv.proyectoviajes.modelos.enumeradores.CargosUsuario;

@RequestScoped
public class Usuario extends Persona {

    private String username;
    private String password;
    private String email;
    private CargosUsuario cargo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CargosUsuario getCargo() {
        return cargo;
    }

    public void setCargo(CargosUsuario cargo) {
        this.cargo = cargo;
    }
}
