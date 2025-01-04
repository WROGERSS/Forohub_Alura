package com.aluracursos.forohub.domain.topico.validaciones;


import com.aluracursos.forohub.domain.topico.RegisterTopicDTO;
import com.aluracursos.forohub.domain.topico.Topic;
import com.aluracursos.forohub.repository.TopicRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DuplicateTopics implements Validations {

    private TopicRepository repository;

    @Autowired
    public DuplicateTopics(TopicRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validar(RegisterTopicDTO datos) {
        Optional<Topic> topico =repository.findByTituloAndMensaje(datos.titulo(),datos.mensaje());
        if(topico.isPresent()){
            throw new ValidationException("Ya existe un mismo tópico con ese título y mensaje");
        }
    }
}
