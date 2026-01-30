package org.sdv.proyectoviajes.servicios;

import org.sdv.proyectoviajes.dto.ObjetoSelectDto;
import org.sdv.proyectoviajes.modelos.Camion;

import java.util.List;

public interface ServicioCamion {
    void guardarCamion(Camion camion);
    void eliminarCamion(Long id);
    List<Camion> buscarTodosCamiones();
    Camion buscarPorIdCamion(Long id);
    List<ObjetoSelectDto> listarCamionesParaSelect();
}
