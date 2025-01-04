package com.aluracursos.forohub.repository;

import com.aluracursos.forohub.domain.topico.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {


    @Query("SELECT t FROM Topico t WHERE t.titulo = :titulo AND t.mensaje = :mensaje")
    Optional<Topic> findByTituloAndMensaje(String titulo, String mensaje);

    Optional<Topic> findById(Long id);

}
