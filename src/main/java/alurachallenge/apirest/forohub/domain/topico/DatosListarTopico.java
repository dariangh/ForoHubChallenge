package alurachallenge.apirest.forohub.domain.topico;

import alurachallenge.apirest.forohub.domain.curso.Curso;
import alurachallenge.apirest.forohub.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public record DatosListarTopico(

        String titulo,

        String mensaje,
        LocalDateTime fechaCreacion,

        boolean status,

        Usuario usuario,

        Curso curso
) {
    public DatosListarTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.isStatus(),
                topico.getUsuario(),
                topico.getCurso()
        );
    }

}

