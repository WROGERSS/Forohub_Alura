package com.aluracursos.forohub.infra.security;

import com.aluracursos.forohub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio de autenticación que implementa {@link UserDetailsService}.
 * Carga los detalles del usuario para la autenticación basada en el correo electrónico.
 */
@Service
public class AuthenticacionService implements UserDetailsService {//Aca empieza la logica para hacer login y aplicar seguridad a mi app

    private UserRepository usuarioRepository;

    /**
     * Constructor de la clase {@link AuthenticacionService}.
     *
     * @param usuarioRepository El repositorio de usuarios que proporciona acceso a los datos del usuario.
     */
    @Autowired
    public AuthenticacionService(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Carga los detalles del usuario basado en el nombre de usuario (correo electrónico).
     *
     * @param username El correo electrónico del usuario que se está cargando.
     * @return Un {@link UserDetails} que contiene la información del usuario.
     * @throws UsernameNotFoundException Si el usuario no es encontrado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByCorreoElectronico(username);
    }

}