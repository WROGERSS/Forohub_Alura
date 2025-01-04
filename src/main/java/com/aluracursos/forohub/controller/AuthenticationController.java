package com.aluracursos.forohub.controller;


import com.aluracursos.forohub.domain.usuario.DataAuthenticationUserDTO;
import com.aluracursos.forohub.domain.usuario.User;
import com.aluracursos.forohub.infra.security.DataJWTToken;
import com.aluracursos.forohub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Autentificación")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    /**
     * Constructor de la clase {@link AuthenticationController}.
     *
     * @param authenticationManager El administrador de autenticación que maneja la autenticación de usuarios.
     * @param tokenService El servicio de tokens que maneja la generación y verificación de tokens JWT.
     */
    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    /**
     * Autentica al usuario con las credenciales proporcionadas y genera un token JWT.
     *
     * @param datosAutenticacionUsuario Los datos de autenticación del usuario (correo y contraseña).
     * @return Un {@link ResponseEntity} que contiene el token JWT en caso de autenticación exitosa.
     */
    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DataAuthenticationUserDTO datosAutenticacionUsuario) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.correo(), datosAutenticacionUsuario.contrasena());
        authenticationManager.authenticate(authenticationToken);//Aca espera el toquen que hay en mi metodo login
        Authentication usuarioAutenticado=authenticationManager.authenticate(authenticationToken);

        String JWtoken = tokenService.generarToken((User) usuarioAutenticado.getPrincipal() );
        return ResponseEntity.ok(new DataJWTToken(JWtoken));
    }
}
