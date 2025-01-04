package com.aluracursos.forohub.domain.topico;


import com.aluracursos.forohub.domain.curso.AnswerCourseDTO;
import com.aluracursos.forohub.domain.usuario.AnswerUserDTO;

import java.time.LocalDate;

public record LongTopicAnswerDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaCreacion,
        State estado,
        AnswerUserDTO autor,
        AnswerCourseDTO curso
) {

    public LongTopicAnswerDTO(Topic datos){
        this(
                datos.getId(),
                datos.getTitulo(),
                datos.getMensaje(),
                datos.getFechaCreacion(),
                datos.getStatus(),
                new AnswerUserDTO(datos.getAutor()),
                new AnswerCourseDTO(datos.getCurso())
        );

    }

}
