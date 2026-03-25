package org.sdv.proyectoviajes.servicios;

import org.sdv.proyectoviajes.dto.ViajeComisionDto;
import org.sdv.proyectoviajes.modelos.Viaje;

import java.util.List;

public interface ServicioViajes {
    void guardarViaje(Viaje viaje);
    void eliminarViaje(Long id);
    List<Viaje> buscarTodosViajes();
    Viaje buscarPorIdViaje(Long id);
    List<ViajeComisionDto> obtenerViajeYComisionPorChofer(Long idChofer);
}
