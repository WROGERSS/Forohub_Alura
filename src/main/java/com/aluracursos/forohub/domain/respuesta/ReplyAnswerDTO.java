package com.aluracursos.forohub.domain.respuesta;

import java.time.LocalDateTime;

public record ReplyAnswerDTO(
        Long id,
        String mensaje,
        Long topico_id,
        LocalDateTime fechaCreacion,
        Long autor_id
) {
    public ReplyAnswerDTO(Answer respuesta) {
        this(respuesta.getId(),respuesta.getMensaje(),respuesta.getTopico().getId(), LocalDateTime.now(),respuesta.getAutor().getId());
    }
}
