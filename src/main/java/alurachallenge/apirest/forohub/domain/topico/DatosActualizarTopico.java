package alurachallenge.apirest.forohub.domain.topico;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        Date fechaCreacion,
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
