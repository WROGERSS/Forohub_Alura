package com.aluracursos.forohub.service;

import com.aluracursos.forohub.domain.curso.UpdateCourseDTO;
import com.aluracursos.forohub.domain.curso.Course;
import com.aluracursos.forohub.domain.curso.RegisterCourseDTO;
import com.aluracursos.forohub.domain.curso.AnswerCourseDTO;
import com.aluracursos.forohub.infra.errores.IntegrityValidation;
import com.aluracursos.forohub.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository cursoRepository;

    @Transactional
    public AnswerCourseDTO registrarCurso(RegisterCourseDTO datos) {
        Optional<Course> cursoBuscado=cursoRepository.findByNombreAndCategoria(datos.nombre(),datos.categoria());
        if(cursoBuscado.isPresent()){
            throw new IntegrityValidation("Ya existe un curso con ese nombre en esa categoria");
        }
        Course nuevoCurso = new Course(datos);
        cursoRepository.save(nuevoCurso);
        AnswerCourseDTO response = new AnswerCourseDTO(nuevoCurso);
        return response;
    }

    @Transactional
    public Page<AnswerCourseDTO> listarCursos(Pageable paginacion) {
        return cursoRepository.findAll(paginacion).map(AnswerCourseDTO::new);
    }

    public AnswerCourseDTO getCursoPorId(Long id) {
        Optional<Course> curso  = cursoRepository.findById(id);
        if(!curso.isPresent()){
            throw new RuntimeException("Curso no encontrado");
        }
        return new AnswerCourseDTO(curso.get());
    }

    @Transactional
    public AnswerCourseDTO actualizarCurso(Long id, UpdateCourseDTO datos) {
        Optional<Course> cursoBuscado = cursoRepository.findById(id);
        if (!cursoBuscado.isPresent()) {
            throw new IntegrityValidation("No hay cursos que correspondan a ese Id");
        }
        Course curso = cursoBuscado.get();
        curso.actualizarCurso(datos);
        cursoRepository.save(curso);
        return new AnswerCourseDTO(curso);
    }
}
