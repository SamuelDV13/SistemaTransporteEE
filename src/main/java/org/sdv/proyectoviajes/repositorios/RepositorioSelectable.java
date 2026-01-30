package org.sdv.proyectoviajes.repositorios;

import org.sdv.proyectoviajes.dto.ObjetoSelectDto;

import java.sql.SQLException;
import java.util.List;

public interface RepositorioSelectable {

    List<ObjetoSelectDto> listarParaSelect() throws SQLException;

}
