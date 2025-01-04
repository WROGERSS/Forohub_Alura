package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.respuesta.UpdateAnswerDTO;
import com.aluracursos.forohub.domain.respuesta.RegisterAnswerDTO;
import com.aluracursos.forohub.domain.respuesta.ReplyAnswerDTO;
import com.aluracursos.forohub.service.AnswerService;
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
@RequestMapping("/respuestas")
@Tag(name = "Respuestas")
@SecurityRequirement(name = "bearer-key")
public class AnswerController {

    private final AnswerService respuestaService;

    @Autowired
    public AnswerController(AnswerService respuestaService) {
        this.respuestaService = respuestaService;
    }

    @Operation(summary = "Crear una nueva respuesta", description = "Crea una nueva respuesta en el Foro.")
    @PostMapping
    public ResponseEntity<ReplyAnswerDTO> crearRespuesta(@RequestBody @Valid RegisterAnswerDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        ReplyAnswerDTO response = respuestaService.registrarRespuesta(datos);
        URI uri = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Listar todas las respuestas", description = "Devuelve una lista de todas las respuestas disponibles.")
    @GetMapping
    public ResponseEntity<Page<ReplyAnswerDTO>> listarRespuestas(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable paginacion) {
        Page<ReplyAnswerDTO> page = respuestaService.listarRespuestas(paginacion);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Obtener una respuesta por ID", description = "Devuelve una respuesta basada en un ID proporcionado.")
    @GetMapping("/{id}")
    public ResponseEntity<ReplyAnswerDTO> obtenerRespuestaPorId(@PathVariable Long id) {
        ReplyAnswerDTO respuesta = respuestaService.getRespuestaPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    @Operation(summary = "Actualizar una respuesta existente", description = "Actualiza la información de una respuesta existente.")
    @PutMapping("/{id}")
    public ResponseEntity<ReplyAnswerDTO> actualizarRespuesta(@PathVariable Long id, @RequestBody @Valid UpdateAnswerDTO datos) {
        ReplyAnswerDTO response = respuestaService.actualizarRespuesta(id, datos);
        return ResponseEntity.ok(response);
    }
}
