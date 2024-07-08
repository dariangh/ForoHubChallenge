package alurachallenge.apirest.forohub.domain.perfil;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarPerfil(
        @NotNull
        Long id,
        String nombre) {
}
