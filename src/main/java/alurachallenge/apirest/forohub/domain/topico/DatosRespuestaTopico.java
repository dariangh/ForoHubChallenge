package alurachallenge.apirest.forohub.domain.topico;

import alurachallenge.apirest.forohub.domain.curso.Curso;
import alurachallenge.apirest.forohub.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,

        Date fechaCreacion,
        boolean status,
        Usuario usuario,
        Curso curso


) {

}
