package com.aluracursos.forohub.domain.curso;

public record AnswerCourseDTO(
        Long id,
        String nombre,
        Category categoria
) {
    public AnswerCourseDTO(Course curso) {
        this(curso.getId(),curso.getNombre(),curso.getCategoria());
    }
}
