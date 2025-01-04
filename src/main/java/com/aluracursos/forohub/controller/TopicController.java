package com.aluracursos.forohub.controller;


import com.aluracursos.forohub.domain.topico.*;
import com.aluracursos.forohub.service.TopicService;
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
@RequestMapping("/topicos")
@Tag(name = "Topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    private TopicService service;

    @Autowired
    public TopicController(TopicService service) {
        this.service = service;
    }

    @Operation(summary = "Registrar un nuevo tópico", description = "Crea un nuevo tópico en el sistema.")
    @PostMapping
    public ResponseEntity<TopicAnswerDTO> registrarTopico(@RequestBody @Valid RegisterTopicDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        TopicAnswerDTO response = service.registrarTopico(datos);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Listar todos los tópicos", description = "Devuelve una lista paginada de todos los tópicos disponibles.")
    @GetMapping
    public ResponseEntity<Page<LongTopicAnswerDTO>> listarConsultas(@PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {
        Page page = service.listaTopicos(paginacion);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Obtener un tópico por ID", description = "Devuelve un tópico basado en el ID proporcionado.")
    @GetMapping("/{id}")
    public ResponseEntity<LongTopicAnswerDTO> obtenerTopicoPorId(@PathVariable Long id) {
        LongTopicAnswerDTO response = service.getTopicoPorId(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Actualizar un tópico existente", description = "Actualiza la información de un tópico existente.")
    @PutMapping("/{id}")
    public ResponseEntity<LongTopicAnswerDTO> actualizarTopico(@PathVariable Long id, @RequestBody @Valid UpdateTopicDTO datos) {
        LongTopicAnswerDTO response = service.actualizarTopico(id, datos);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Eliminar un tópico", description = "Elimina un tópico del sistema.")
    @DeleteMapping("/{id}")
    public ResponseEntity borrarTopico(@PathVariable Long id) {
        service.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }

}
