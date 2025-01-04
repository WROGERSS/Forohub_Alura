package com.aluracursos.forohub.domain.usuario;

public record AnswerUserDTO(
        Long id,
        String nombre
) {
    public AnswerUserDTO(User datos) {
        this( datos.getId(),datos.getNombre());
    }
}
