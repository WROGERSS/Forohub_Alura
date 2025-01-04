package com.aluracursos.forohub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterCourseDTO(
        @NotBlank
        String nombre,
        @NotNull
        Category categoria
) {
}
