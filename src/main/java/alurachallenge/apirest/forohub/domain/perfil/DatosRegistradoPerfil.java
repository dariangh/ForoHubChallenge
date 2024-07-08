package alurachallenge.apirest.forohub.domain.perfil;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistradoPerfil(
        @NotBlank
        String nombre
) {
}
