package org.sdv.proyectoviajes.dto;

public class ObjetoSelectDto {

    private Long id;
    private String texto;

    public ObjetoSelectDto(Long id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public Long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

}
