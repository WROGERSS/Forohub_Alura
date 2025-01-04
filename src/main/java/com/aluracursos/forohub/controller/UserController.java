package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.usuario.UpdateUserDTO;
import com.aluracursos.forohub.domain.usuario.RegisterUserDTO;
import com.aluracursos.forohub.domain.usuario.AnswerUserDTO;
import com.aluracursos.forohub.service.UserService;
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
@RequestMapping("/usuarios")
@Tag(name = "Usuarios")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Registrar un nuevo usuario", description = "Crea un nuevo usuario en el Foro.")
    @PostMapping
    public ResponseEntity<AnswerUserDTO> crearUsuario(@RequestBody @Valid RegisterUserDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        AnswerUserDTO response = service.registrarUsuario(datos);
        URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Listar todos los usuarios", description = "Devuelve una lista de todos los usuarios registrados.")
    @GetMapping
    public ResponseEntity<Page<AnswerUserDTO>> listarUsuarios(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable paginacion) {
        Page page = service.listarUsuarios(paginacion);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Obtener un usuario por ID", description = "Devuelve un usuario basado en un ID proporcionado.")
    @GetMapping("/{id}")
    public ResponseEntity<AnswerUserDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        AnswerUserDTO response = service.getUsuarioPorId(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Actualizar un usuario existente", description = "Actualiza la información de un usuario existente.")
    @PutMapping("/{id}")
    public ResponseEntity<AnswerUserDTO> actualizarUsuario(@PathVariable Long id, @RequestBody @Valid UpdateUserDTO datos) {
        AnswerUserDTO response = service.actualizarUsuario(id, datos);
        return ResponseEntity.ok(response);
    }

}
