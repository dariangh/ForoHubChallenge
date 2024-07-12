package alurachallenge.apirest.forohub.domain.respuesta;

import alurachallenge.apirest.forohub.domain.topico.Topico;
import alurachallenge.apirest.forohub.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public record DatosListarRespuesta(
        String mensaje,
        Topico topico,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime fechaCreacion,

        Usuario usuario

//        String solucion
        ) {
        public DatosListarRespuesta(Respuesta respuesta) {
                this(
                        respuesta.getMensaje(),
                        respuesta.getTopico(),
                        respuesta.getFechaCreacion(),
                        respuesta.getUsuario()
//                        respuesta.getSolucion()
                );
        }
//        public DatosListarRespuesta(Topico topico) {
//                this(
//                        topico.getTitulo(),
//                        topico.getMensaje(),
//                        topico.getFechaCreacion(),
//                        topico.isStatus(),
//                        topico.getUsuario(),
//                        topico.getCurso()
//                );
//        }
}
