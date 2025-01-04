package com.aluracursos.forohub.controller;


import com.aluracursos.forohub.domain.curso.UpdateCourseDTO;
import com.aluracursos.forohub.domain.curso.RegisterCourseDTO;
import com.aluracursos.forohub.domain.curso.AnswerCourseDTO;
import com.aluracursos.forohub.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Cursos")
@SecurityRequirement(name = "bearer-key")
public class CourseController {

    private final CourseService cursoService;

    @Autowired
    public CourseController(CourseService cursoService) {
        this.cursoService = cursoService;
    }

    @Operation(summary = "Crear un nuevo curso", description = "Crea un nuevo curso en el Foro.")
    @PostMapping
    public ResponseEntity<AnswerCourseDTO> crearCurso(@RequestBody @Valid RegisterCourseDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        AnswerCourseDTO response = cursoService.registrarCurso(datos);
        URI uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Listar todos los cursos", description = "Devuelve una lista de todos los cursos disponibles.")
    @GetMapping
    public ResponseEntity<Page<AnswerCourseDTO>> listarCursos(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable paginacion) {
        Page<AnswerCourseDTO> page = cursoService.listarCursos(paginacion);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Obtener un curso por ID", description = "Devuelve un curso basado en un ID proporcionado.")
    @GetMapping("/{id}")
    public ResponseEntity<AnswerCourseDTO> obtenerCursoPorId(@PathVariable Long id) {
        AnswerCourseDTO response = cursoService.getCursoPorId(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Actualizar un curso existente", description = "Actualiza la información de un curso existente.")
    @PutMapping("/{id}")
    public ResponseEntity<AnswerCourseDTO> actualizarCurso(@PathVariable Long id, @RequestBody @Valid UpdateCourseDTO datos) {
        AnswerCourseDTO response = cursoService.actualizarCurso(id, datos);
        return ResponseEntity.ok(response);
    }

}
