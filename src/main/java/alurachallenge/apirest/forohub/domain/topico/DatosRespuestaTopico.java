package alurachallenge.apirest.forohub.domain.topico;

import alurachallenge.apirest.forohub.domain.curso.Curso;
import alurachallenge.apirest.forohub.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime fechaCreacion,
        boolean status,
        Usuario usuario,
        Curso curso


) {

    public DatosRespuestaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.isStatus(),
                topico.getUsuario(),
                topico.getCurso()
        );
    }
}
