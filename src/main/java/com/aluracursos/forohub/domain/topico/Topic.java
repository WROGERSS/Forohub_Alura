package com.aluracursos.forohub.domain.topico;


import com.aluracursos.forohub.domain.curso.Course;
import com.aluracursos.forohub.domain.respuesta.Answer;
import com.aluracursos.forohub.domain.usuario.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensaje;

    @Column(name = "fecha_creacion") // Aquí ajusta el nombre de la columna según la base de datos
    private LocalDate fechaCreacion;

    @Enumerated(EnumType.STRING)
    private State status;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private User autor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Course curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Answer> respuestas;

    public Topic(RegisterTopicDTO datos , User datosUsuario, Course curso){
        this.id=null;
        this.titulo=datos.titulo();
        this.mensaje=datos.mensaje();
        this.fechaCreacion=LocalDate.now();
        this.status= State.ACTIVO;
        this.autor=datosUsuario;
        this.curso=curso;
    }

    public void actualizarDatos(UpdateTopicDTO datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
    }

    public void agregarRespuesta(Answer respuesta){
        respuestas.add(respuesta);
    }


}
//}
