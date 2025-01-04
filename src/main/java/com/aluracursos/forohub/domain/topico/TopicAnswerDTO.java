package com.aluracursos.forohub.domain.topico;

import java.time.LocalDate;

public record TopicAnswerDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaCreacion
) {

    public TopicAnswerDTO(Topic datos){
        this(
                datos.getId(),
                datos.getTitulo(),
                datos.getMensaje(),
                datos.getFechaCreacion()
        );

    }

}
