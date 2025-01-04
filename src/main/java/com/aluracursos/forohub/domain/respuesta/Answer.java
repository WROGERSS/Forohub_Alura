package com.aluracursos.forohub.domain.respuesta;


import com.aluracursos.forohub.domain.topico.Topic;
import com.aluracursos.forohub.domain.usuario.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name="Respuesta")
@Table(name = "respuestas")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topic topico;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private User autor;

    public void actualizarRespuesta(UpdateAnswerDTO datos) {
        if(datos.mensaje()!=null){
            this.mensaje=datos.mensaje();
        }
    }

}
