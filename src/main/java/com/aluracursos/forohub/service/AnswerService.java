package com.aluracursos.forohub.service;


import com.aluracursos.forohub.domain.respuesta.UpdateAnswerDTO;
import com.aluracursos.forohub.domain.respuesta.RegisterAnswerDTO;
import com.aluracursos.forohub.domain.respuesta.Answer;
import com.aluracursos.forohub.domain.respuesta.ReplyAnswerDTO;
import com.aluracursos.forohub.domain.topico.Topic;
import com.aluracursos.forohub.domain.usuario.User;
import com.aluracursos.forohub.infra.errores.IntegrityValidation;
import com.aluracursos.forohub.repository.AnswerRepository;
import com.aluracursos.forohub.repository.TopicRepository;
import com.aluracursos.forohub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AnswerService {

    private TopicRepository topicoRepository;
    private UserRepository usuarioRepository;
    private AnswerRepository respuestaRepository;

    @Autowired
    public AnswerService(TopicRepository topicoRepository, UserRepository usuarioRepository, AnswerRepository respuestaRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.respuestaRepository = respuestaRepository;
    }

    @Transactional
    public ReplyAnswerDTO registrarRespuesta(RegisterAnswerDTO datos) {
        //Primero busco el autor y el topico para ver si existen
        Topic topico = topicoRepository.findById(datos.topico_id()).orElseThrow(() -> new IntegrityValidation("No hay ningun tópico con ese id"));
        User autor = usuarioRepository.findById(datos.autor_id()).orElseThrow(() -> new IntegrityValidation("No hay ningun usuario con ese id"));

        Answer respuesta = new Answer(null, datos.mensaje(), topico, LocalDateTime.now(), autor);

        //Tengo que agregar la respuesta a la lista del topico
        topico.agregarRespuesta(respuesta);

        respuestaRepository.save(respuesta);

        return new ReplyAnswerDTO(respuesta);

    }

    public Page<ReplyAnswerDTO> listarRespuestas(Pageable respuestas) {
        Page page = respuestaRepository.findAll(respuestas).map(ReplyAnswerDTO::new);
        return page;
    }

    public ReplyAnswerDTO getRespuestaPorId(Long id) {
        Optional<Answer> respuesta = respuestaRepository.findById(id);
        if (!respuesta.isPresent()) {
            throw new IntegrityValidation("No hay respuestas que correspondan a ese Id");
        }
        return new ReplyAnswerDTO(respuesta.get());
    }

    @Transactional
    public ReplyAnswerDTO actualizarRespuesta(Long id, UpdateAnswerDTO datos) {
        Optional<Answer> respuestaBuscada = respuestaRepository.findById(id);
        if (!respuestaBuscada.isPresent()) {
            throw new IntegrityValidation("No hay respuestas que correspondan a ese Id");
        }
        Answer respuesta = respuestaBuscada.get();
        respuesta.actualizarRespuesta(datos);
        respuestaRepository.save(respuesta);
        return new ReplyAnswerDTO(respuesta);
    }


}
