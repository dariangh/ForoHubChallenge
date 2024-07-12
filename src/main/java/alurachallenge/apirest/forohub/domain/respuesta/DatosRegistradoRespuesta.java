package alurachallenge.apirest.forohub.domain.respuesta;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistradoRespuesta(
        @NotBlank
        String mensaje,
        @NotNull
        @Valid
        Long idTopico,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime fechaCreacion,
        @NotNull
        @Valid
        Long idUsuario
//        @NotBlank
//        String solucion
) {
}
