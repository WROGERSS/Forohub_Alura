package com.aluracursos.forohub.repository;

import com.aluracursos.forohub.domain.respuesta.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer,Long> {

    Optional<Answer> findById(Long id);

}
