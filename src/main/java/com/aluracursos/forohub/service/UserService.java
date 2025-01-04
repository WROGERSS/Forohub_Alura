package com.aluracursos.forohub.service;


import com.aluracursos.forohub.domain.usuario.UpdateUserDTO;
import com.aluracursos.forohub.domain.usuario.RegisterUserDTO;
import com.aluracursos.forohub.domain.usuario.AnswerUserDTO;
import com.aluracursos.forohub.domain.usuario.User;
import com.aluracursos.forohub.infra.errores.IntegrityValidation;
import com.aluracursos.forohub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AnswerUserDTO registrarUsuario(RegisterUserDTO datos) {
        //Verificamos que no este ya creado
        Optional<User> usuarioBuscado = usuarioRepository.findByCorreo(datos.correo());

        if (usuarioBuscado.isPresent()) {
            throw new IntegrityValidation("El correo " + datos.correo() + " ya esta registrado");
        }

        String nombreUsuario = datos.nombre();
        String correoElectronico = datos.correo();
        String contrasena = datos.contrasena();

        //Ahora encriptamos la contraseña
        contrasena = passwordEncoder.encode(contrasena);

        User nuevoUsuario = new User(null, nombreUsuario, correoElectronico, contrasena);

        usuarioRepository.save(nuevoUsuario);

        return new AnswerUserDTO(nuevoUsuario);
    }

    public Page<AnswerUserDTO> listarUsuarios(Pageable usuarios) {
        Page page = usuarioRepository.findAll(usuarios).map(AnswerUserDTO::new);
        return page;
    }

    @Transactional
    public AnswerUserDTO actualizarUsuario(Long id, UpdateUserDTO datos) {
        Optional<User> usuarioBuscado = usuarioRepository.findById(id);
        if (!usuarioBuscado.isPresent()) {
            throw new IntegrityValidation("No hay usuarios que correspondan a ese Id");
        }
        User usuario = usuarioBuscado.get();
        usuario.actualizarDatos(datos);
        usuarioRepository.save(usuario);
        return new AnswerUserDTO(usuario);
    }

    public AnswerUserDTO getUsuarioPorId(Long id) {
        Optional<User> usuario = usuarioRepository.findById(id);
        if (!usuario.isPresent()) {
            throw new IntegrityValidation("No hay usuarios que correspondan a ese Id");
        }
        return new AnswerUserDTO(usuario.get());
    }
}

