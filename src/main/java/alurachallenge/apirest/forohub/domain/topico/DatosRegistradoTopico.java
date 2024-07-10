package alurachallenge.apirest.forohub.domain.topico;

import alurachallenge.apirest.forohub.domain.curso.Curso;
import alurachallenge.apirest.forohub.domain.respuesta.Respuesta;
import alurachallenge.apirest.forohub.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

public record DatosRegistradoTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime fechaCreacion,
        @NotNull
        boolean status,
        @NotNull
        @Valid
        Long idUsuario,
        @NotNull
        @Valid
        Long idCurso

) {
}
