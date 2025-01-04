package com.aluracursos.forohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record RegisterUserDTO(
        @NotBlank String nombre,
        @NotBlank String correo,
        @NotBlank String contrasena
) {
}
