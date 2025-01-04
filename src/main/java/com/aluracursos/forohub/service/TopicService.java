package com.aluracursos.forohub.service;

import com.aluracursos.forohub.domain.curso.Course;
import com.aluracursos.forohub.domain.topico.*;
import com.aluracursos.forohub.domain.topico.validaciones.Validations;
import com.aluracursos.forohub.domain.usuario.User;
import com.aluracursos.forohub.infra.errores.IntegrityValidation;
import com.aluracursos.forohub.repository.CourseRepository;
import com.aluracursos.forohub.repository.TopicRepository;
import com.aluracursos.forohub.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private TopicRepository topicoRepository;
    private UserRepository usuarioRepository;
    private CourseRepository cursoRepository;
    private List<Validations> validaciones;

    @Autowired
    public TopicService(List<Validations> validaciones, TopicRepository topicoRepository, UserRepository usuarioRepository, CourseRepository curso) {
        this.validaciones = validaciones;
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = curso;
    }

    @Transactional
    public TopicAnswerDTO registrarTopico(RegisterTopicDTO datos) {
        Optional<User> usuario = usuarioRepository.findById(datos.autor_id());
        Optional<Course> curso = cursoRepository.findById(datos.curso_id());

        if (!usuario.isPresent()) {
            throw new ValidationException("Usuario inexistente");
        }

        if (!curso.isPresent()) {
            throw new ValidationException("Curso inexistente");
        }

        var usuarioEncontrado = usuario.get();
        var cursoEncontrado = curso.get();

        validaciones.forEach(validacion -> validacion.validar(datos));
        Topic topico = new Topic(datos, usuarioEncontrado, cursoEncontrado);
        topicoRepository.save(topico);
        TopicAnswerDTO response = new TopicAnswerDTO(topico);
        return response;
    }

    public Page<LongTopicAnswerDTO> listaTopicos(Pageable topicos) {
        Page page = topicoRepository.findAll(topicos).map(LongTopicAnswerDTO::new);
        return page;
    }


    public LongTopicAnswerDTO getTopicoPorId(Long id) {
        Optional<Topic> topico = topicoRepository.findById(id);
        if (!topico.isPresent()) {
            throw new IntegrityValidation("No hay tópico que correspondan a ese Id");
        }
        return new LongTopicAnswerDTO(topico.get());
    }

    @Transactional
    public LongTopicAnswerDTO actualizarTopico(Long id, UpdateTopicDTO datos) {
        Optional<Topic> topicoBuscado = topicoRepository.findById(id);
        if (!topicoBuscado.isPresent()) {
            throw new IntegrityValidation("No hay topicos que correspondan a ese Id");
        }
        Topic topico = topicoBuscado.get();
        topico.actualizarDatos(datos);
        topicoRepository.save(topico);
        return new LongTopicAnswerDTO(topico);
    }

    @Transactional
    public void eliminarTopico(Long id) {
        Optional<Topic> topicoBuscado = topicoRepository.findById(id);
        if (!topicoBuscado.isPresent()) {
            throw new IntegrityValidation("No hay topicos que correspondan a ese Id");
        }
        Topic topico = topicoBuscado.get();
        topicoRepository.delete(topico);
    }
}
