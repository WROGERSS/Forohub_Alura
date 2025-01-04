package com.aluracursos.forohub.repository;


import com.aluracursos.forohub.domain.curso.Category;
import com.aluracursos.forohub.domain.curso.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    Optional<Course> findByNombre(String nombre);

    Optional<Course> findByNombreAndCategoria(String nombre, Category categoria);
}
