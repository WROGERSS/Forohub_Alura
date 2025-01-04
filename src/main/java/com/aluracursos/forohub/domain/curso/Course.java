package com.aluracursos.forohub.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Curso")
@Table(name = "cursos")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Category categoria;

    public Course(RegisterCourseDTO datos) {
        this.id = null;
        this.nombre = datos.nombre();
        this.categoria = datos.categoria();
    }

    public Course(AnswerCourseDTO datos) {
        this.id=datos.id();
        this.nombre = datos.nombre();
        this.categoria = datos.categoria();
    }



    public void actualizarCurso(UpdateCourseDTO curso) {
        if (curso.nombre() != null) {
            this.nombre = curso.nombre();
        }
        if (curso.categoria() != null) {
            this.categoria = curso.categoria();
        }
    }
}
